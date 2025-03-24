package com.hertz.shoppingMall.login.service;

import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder pwEncoder;

    public Member login(String loginId, String password){

        Member member = memberRepository.findByLoginId(loginId);
        //암호화해서 찾아야함
        if(member != null && pwEncoder.matches(password, member.getPassword())){
            return member;
        }else{
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        // 여기서는 사용자 데이터만 불러오고, 비밀번호 검증은 Spring Security가 처리
        Member member = memberRepository.findByLoginId(loginId);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + member.getRole().name()));

        return new User(member.getLoginId(), member.getPassword(), authorities);
    }
}
