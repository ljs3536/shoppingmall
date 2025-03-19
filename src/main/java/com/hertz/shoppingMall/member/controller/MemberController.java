package com.hertz.shoppingMall.member.controller;

import com.hertz.shoppingMall.member.dto.MemberForm;
import com.hertz.shoppingMall.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/form")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "member/form";
    }

    @PostMapping("/members/form")
    public String create(@Valid MemberForm form, BindingResult result){

        return "redirect:/";
    }
}
