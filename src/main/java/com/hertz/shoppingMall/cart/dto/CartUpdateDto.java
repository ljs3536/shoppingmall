package com.hertz.shoppingMall.cart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartUpdateDto {
    private Long cartItemId;
    private int quantity;
}
