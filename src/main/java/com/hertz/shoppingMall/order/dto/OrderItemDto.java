package com.hertz.shoppingMall.order.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class OrderItemDto {

    private Long productId;
    private String productName;
    @Min(value = 1, message = "주문 수량은 최소 1개 이상이어야 합니다.")
    private int quantity;
    private int price;


}
