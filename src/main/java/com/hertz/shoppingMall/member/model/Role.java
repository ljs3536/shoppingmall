package com.hertz.shoppingMall.member.model;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ROLE_ADMIN", 0),
    SELLER("ROLE_SELLER", 1),
    BUYER("ROLE_BUYER", 2);

    private final String key;
    private final int value;

    Role(String key, int value) {
        this.key = key;
        this.value = value;
    }

}
