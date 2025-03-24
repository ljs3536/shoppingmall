package com.hertz.shoppingMall.member.controller;

import com.hertz.shoppingMall.member.dto.MemberForm;
import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final PasswordEncoder pwEncoder;

    // 회원 등록 폼 페이지
    @GetMapping("/members/form")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    // 회원 등록 
    @PostMapping("/members/form")
    public String create(@Valid MemberForm form, BindingResult result){

        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        Member member = new Member();
        member.setUsername(form.getUsername());
        member.setNickname(form.getNickname());
        member.setRegion(form.getRegion());
        member.setRealAddress(form.getRealAddress());
        member.setAge(form.getAge());
        member.setGender(form.getGender());
        member.setEmailAddress(form.getEmailAddress());
        member.setCellNo(form.getCellNo());
        member.setLoginId(form.getLoginId());
        member.setPassword(pwEncoder.encode(form.getPassword()));
        member.setRole(form.getRole());
        memberService.saveMember(member);
        return "redirect:/";
    }

    // 회원 전체 조회
    @GetMapping("/admin/members")
    @PreAuthorize("hasRole('ADMIN')")
    public String list(Model model){
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/members/view/{id}")
    public String view(@PathVariable("id") Long id, Model model){
        Member member = memberService.getMember(id);
        model.addAttribute("member", member);
        return "members/memberView";
    }

    @GetMapping("/test/{loginId}")
    @ResponseBody
    public String testMember(@PathVariable("loginId") String loginId) {
        Member member = memberService.getMemberByLoginId(loginId);
        if (member == null) {
            return "회원을 찾을 수 없습니다: " + loginId;
        }
        return "회원 정보: ID=" + member.getLoginId() + ", 역할=" + member.getRole();
    }
}
