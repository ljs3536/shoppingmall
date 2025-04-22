package com.hertz.shoppingMall.member.service;


import com.hertz.shoppingMall.member.dto.MemberForm;
import com.hertz.shoppingMall.member.dto.MemberUpdateForm;
import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.member.repository.MemberRepository;
import com.hertz.shoppingMall.utils.exception.custom.DuplicateMemberException;
import com.hertz.shoppingMall.utils.page.PageRequestDto;
import com.hertz.shoppingMall.utils.search.SearchRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void modifyMember(MemberUpdateForm form) {
        Member member = memberRepository.findById(form.getId())
                .orElseThrow(()-> new IllegalArgumentException("해당 계정이 없습니다."));

        member.setGender(form.getGender());
        member.setNickname(form.getNickname());
        member.setAge(form.getAge());
        member.setUsername(form.getUsername());
        member.setRole(form.getRole());
        member.setLoginId(form.getLoginId());
        member.setCellNo(form.getCellNo());
        member.setEmailAddress(form.getEmailAddress());
        member.setRegion(form.getRegion());
        member.setRealAddress(form.getRealAddress());

    }

    @Transactional
    public void modifyMemberPassword(Long id, String encodedPassword) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 계정이 없습니다."));

        member.setPassword(encodedPassword);
    }
}
