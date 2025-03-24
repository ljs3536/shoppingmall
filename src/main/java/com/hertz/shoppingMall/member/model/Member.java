package com.hertz.shoppingMall.member.model;

import com.hertz.shoppingMall.config.jpa.BaseDateEntity;
import com.hertz.shoppingMall.product.model.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "createdBy")
    private List<Product> createdProducts = new ArrayList<>();

    @OneToMany(mappedBy = "modifiedBy")
    private List<Product> modifiedProducts = new ArrayList<>();
}
