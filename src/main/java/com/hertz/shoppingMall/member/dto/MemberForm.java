package com.hertz.shoppingMall.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {


    private String username;
    private String nickname;
    private String region;
    private String age;
    private String gender;
    private String realAddress;
    private String emailAddress;
    private String cellNo;
}
