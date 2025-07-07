package com.hertz.shoppingMall.config.security;

import com.hertz.shoppingMall.login.service.LoginService;
import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import java.util.ArrayList;
import java.util.List;

import static com.hertz.shoppingMall.member.model.Role.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final MemberService memberService;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authProvider);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            System.out.println("Login failed: " + exception.getMessage());

            String errorMessage;
            if (exception instanceof BadCredentialsException) {
                errorMessage = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
            } else if (exception instanceof UsernameNotFoundException) {
                errorMessage = "계정이 존재하지 않습니다. 회원가입 진행 후 로그인 해주세요.";
            } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
                errorMessage = "인증 요청이 거부되었습니다. 관리자에게 문의하세요.";
            } else if (exception instanceof InternalAuthenticationServiceException){
                errorMessage = exception.getMessage();
            }else {
                errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다 관리자에게 문의하세요.";
            }
            // 예외 메시지를 세션에 저장하여 화면에서 표시 가능
            request.getSession().setAttribute("errorMessage", errorMessage);

            response.sendRedirect("/login?error=true");
        };
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception{
        // CSRF 토큰을 요청 속성에 설정하는 핸들러 생성
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 또는 DEFAULT
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/ws/**") //웹소켓 관련 csrf검증 제외
                        // csrf 토큰 요청 핸들러 설정
                        .csrfTokenRequestHandler(requestHandler)
                        // csrf 토큰을 쿠키로 저장, HttpOnly 설정 비활성화
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/seller/**").hasRole("SELLER")
                        .requestMatchers("/buyer/**").hasRole("BUYER")
                        .requestMatchers("/api/**").authenticated()
                        // 여기에 메인 페이지("/")를 permitAll에 추가
                        .requestMatchers("/", "/login", "/members/form", "/resources/**", "/css/**", "/js/**"
                                , "/test/**", "/fragments/**","/actuator/prometheus", "/test/*").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login-process")
                        .defaultSuccessUrl("/", true)
                        .failureHandler(authenticationFailureHandler())
                        .usernameParameter("loginId")
                        .passwordParameter("password")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        // AJAX 요청 시 401 에러 반환하도록 설정
                        .authenticationEntryPoint((request, response, authException) -> {
                            String requestedWith = request.getHeader("X-Requested-With");
                            if ("XMLHttpRequest".equals(requestedWith)) {
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized AJAX request");
                            } else {
                                response.sendRedirect("/login");
                            }
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            System.out.println("Access Denied: " + accessDeniedException.getMessage());
                            response.sendRedirect("/access-denied");
                        })
                )
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin())
                )
                .authenticationManager(authenticationManager);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
                System.out.println("Attempting to load user: " + loginId);

                Member member = memberService.getMemberByLoginId(loginId);
                if (member == null) {
                    System.out.println("User not found with loginId: " + loginId);
                    throw new BadCredentialsException("사용자를 찾을 수 없습니다: " + loginId);
                }

                return new CustomUserDetails(member);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
