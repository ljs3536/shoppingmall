package com.hertz.shoppingMall.member.controller;

import com.hertz.shoppingMall.member.dto.MemberForm;
import com.hertz.shoppingMall.member.dto.MemberPwUpdateDto;
import com.hertz.shoppingMall.member.dto.MemberUpdateForm;
import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.member.service.MemberService;
import com.hertz.shoppingMall.utils.page.PageRequestDto;
import com.hertz.shoppingMall.utils.search.SearchRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/members")
public class AdminMemberController {

    private final MemberService memberService;

    private final PasswordEncoder pwEncoder;

    // 회원 전체 조회
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String list(@ModelAttribute SearchRequestDto searchRequestDto, PageRequestDto pageRequestDto, Model model){
        Page<Member> memberPage  = memberService.getAllMembers(searchRequestDto,pageRequestDto);
        model.addAttribute("members", memberPage.getContent());  // 현재 페이지의 데이터
        model.addAttribute("memberPage", memberPage);  // 페이지 정보
        return "members/memberList";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long id, Model model){
        Member member = memberService.getMember(id);
        model.addAttribute("member", member);
        return "members/memberView";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model){
        Member member = memberService.getMember(id);
        MemberUpdateForm form = new MemberUpdateForm();
        form.setId(member.getId());
        form.setGender(member.getGender());
        form.setAge(member.getAge());
        form.setRole(member.getRole());
        form.setNickname(member.getNickname());
        form.setRegion(member.getRegion());
        form.setLoginId(member.getLoginId());
        form.setCellNo(member.getCellNo());
        form.setEmailAddress(member.getEmailAddress());
        form.setRealAddress(member.getRealAddress());
        form.setUsername(member.getUsername());
        model.addAttribute("memberUpdateForm", form);
        return "members/updateMemberForm";
    }

    @PostMapping("/update")
    public String update(@Valid MemberUpdateForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        memberService.modifyMember(form);

        return "redirect:/";
    }


    @PostMapping("/updatePassword")
    @ResponseBody
    public ResponseEntity<MemberPwUpdateDto> updatePassword(
            @RequestBody @Valid MemberPwUpdateDto form
    ){
        try {
            memberService.modifyMemberPassword(form.getId(), pwEncoder.encode(form.getPassword()));

            return ResponseEntity.ok(form);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
