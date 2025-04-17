package com.hertz.shoppingMall.member.model;

import com.hertz.shoppingMall.config.jpa.BaseDateEntity;
import com.hertz.shoppingMall.config.security.CustomUserDetails;
import com.hertz.shoppingMall.product.model.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseDateEntity implements Serializable {

    @Serial //클래스 버전 관리를 위해
    private static final long serialVersionUID = 2L;    //고유 식별자 필드

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String username;
    private String nickname;
    private String region;
    @NotEmpty
    private String realAddress;
    private Integer age;
    private String gender;
    @NotEmpty
    private String emailAddress;
    @NotEmpty
    private String cellNo;

    @NotEmpty
    @Column(unique = true)
    private String loginId;
    @NotEmpty
    private String password;

    @OneToMany(mappedBy = "createdBy")
    private List<Product> createdProducts = new ArrayList<>();

    @OneToMany(mappedBy = "modifiedBy")
    private List<Product> modifiedProducts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    // 생성 메서드 추가
    public static Member createMember(CustomUserDetails userDetails){
        Member member = new Member();
        member.setId(userDetails.getMemberId());
        member.setLoginId(userDetails.getLoginId());
        member.setUsername(userDetails.getUsername());
        member.setPassword(userDetails.getPassword());
        member.setNickname(userDetails.getNickname());
        member.setRegion(userDetails.getRegion());
        member.setAge(userDetails.getAge());
        member.setGender(userDetails.getGender());
        return member;
    }
}
