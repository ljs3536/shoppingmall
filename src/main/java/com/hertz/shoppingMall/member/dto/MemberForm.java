package com.hertz.shoppingMall.member.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message="회원 이름은 필수 입니다.")
    private String username;
    private String nickname;
    private String region;
    private String age;
    private String gender;
    private String realAddress;
    private String emailAddress;
    private String cellNo;

    @NotEmpty(message="사용자ID는 필수 입니다.")
    private String loginId;
    @NotEmpty(message="비밀번호는 필수 입니다.")
    private String password;
}
