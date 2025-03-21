package com.hertz.shoppingMall.login.service;

import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

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
}
