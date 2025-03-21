package com.hertz.shoppingMall.member.model;

import com.hertz.shoppingMall.config.jpa.BaseDateEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member extends BaseDateEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String username;
    private String nickname;
    private String region;
    private String realAddress;
    private String age;
    private String gender;
    private String emailAddress;
    private String cellNo;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
}
