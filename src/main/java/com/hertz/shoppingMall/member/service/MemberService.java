package com.hertz.shoppingMall.member.service;


import com.hertz.shoppingMall.member.model.MemberVO;
import com.hertz.shoppingMall.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberVO saveMember(MemberVO member){
        return memberRepository.save(member);
    }

    public List<MemberVO> getAllMembers(){
        return memberRepository.findAll();
    }

}
