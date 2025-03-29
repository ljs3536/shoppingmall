package com.hertz.shoppingMall.cart.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class CartItemDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long cartItemId;
    private Long productId;
    private String productName;
    private int quantity;
    private int price;
    private int totalPrice;

    public CartItemDto(Long cartItemId, Long productId, @NotEmpty String productName, int quantity, int price, int totalPrice) {
        this.cartItemId = cartItemId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }
}
