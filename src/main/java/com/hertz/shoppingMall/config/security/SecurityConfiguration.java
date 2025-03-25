package com.hertz.shoppingMall.config.security;

import com.hertz.shoppingMall.login.service.LoginService;
import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

            // 예외 메시지를 세션에 저장하여 화면에서 표시 가능
            request.getSession().setAttribute("errorMessage", exception.getMessage());

            response.sendRedirect("/login?error=true");
        };
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception{
        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/seller/**").hasRole("SELLER")
                        .requestMatchers("/buyer/**").hasRole("BUYER")
                        // 여기에 메인 페이지("/")를 permitAll에 추가
                        .requestMatchers("/", "/login", "/members/form", "/resources/**", "/css/**", "/js/**", "/test/**", "/fragments/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login-process")
                        .defaultSuccessUrl("/")
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
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            System.out.println("Access Denied: " + accessDeniedException.getMessage());
                            response.sendRedirect("/access-denied");
                        })
                ).authenticationManager(authenticationManager);

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
