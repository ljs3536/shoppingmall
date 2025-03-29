package com.hertz.shoppingMall.login.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class LoginForm implements Serializable {

    @Serial //클래스 버전 관리를 위해
    private static final long serialVersionUID = 1L;    //고유 식별자 필드

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;
}
