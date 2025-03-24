package com.hertz.shoppingMall.member.service;


import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByUsername(member.getUsername());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

    public Member getMember(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        return member;
    }

    public Member getMemberByLoginId(String loginId) {
        Member member = memberRepository.findByLoginId(loginId);
        return member;
    }
}
