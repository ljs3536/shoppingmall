package com.hertz.shoppingMall.member.service;


import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.member.repository.MemberRepository;
import com.hertz.shoppingMall.utils.exception.custom.DuplicateMemberException;
import com.hertz.shoppingMall.utils.page.PageRequestDto;
import com.hertz.shoppingMall.utils.search.SearchRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Member findMember = memberRepository.findByLoginId(member.getLoginId());
        if(findMember != null){
            throw new DuplicateMemberException("이미 존재하는 회원입니다.");
        }
    }

    public Page<Member> getAllMembers(SearchRequestDto searchRequestDto, PageRequestDto pageRequestDto){
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize(), Sort.by(pageRequestDto.getSort()).descending());
        return memberRepository.seearchMembers(searchRequestDto,pageable);
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
