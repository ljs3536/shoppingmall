package com.hertz.shoppingMall.cart.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class CartForm implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long productId;
    private int quantity;
}
