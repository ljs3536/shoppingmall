package com.hertz.shoppingMall.member.dto;

import com.hertz.shoppingMall.member.model.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class MemberUpdateForm implements Serializable {

    @Serial //클래스 버전 관리를 위해
    private static final long serialVersionUID = 1L;    //고유 식별자 필드

    private Long id;

    //회원이름
    @NotBlank(message="회원 이름은 필수 입니다.")
    @Size(min = 2, max = 10, message = "회원 이름은 2자에서 10자 사이여야 합니다.")
    private String username;

    //닉네임(별명)
    @Size(max = 10, message="닉네임은 10자를 초과할 수 없습니다.")
    private String nickname;
    //지역
    @Size(max = 20, message = "지역 정보는 100자를 초과할 수 없습니다.")
    private String region;

    //나이
    @Min(value = 0, message = "나이는 0보다 작을 수 없습니다.")
    @Max(value = 150, message = "나이는 150을 초과할 수 없습니다.")
    private Integer age;
    //성별
    private String gender;
    //주소
    @NotBlank(message = "실제 주소는 필수입니다.")
    @Size(max = 255, message = "주소는 255자를 초과할 수 없습니다.")
    private String realAddress;
    //이메일
    @NotBlank(message = "이메일 주소는 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @Size(max = 100, message = "이메일은 100자를 초과할 수 없습니다.")
    private String emailAddress;

    //전화번호
    @NotBlank(message = "연락처는 필수입니다.")
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "올바른 전화번호 형식이 아닙니다 (010-XXXX-XXXX)")
    private String cellNo;

    // 로그인 ID
    @NotBlank(message = "로그인 ID는 필수입니다")
    @Size(min = 4, max = 20, message = "로그인 ID는 4자에서 20자 사이여야 합니다")
    private String loginId;


    private Role role;
}
