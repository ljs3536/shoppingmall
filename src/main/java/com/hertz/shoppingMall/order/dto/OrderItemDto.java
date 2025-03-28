package com.hertz.shoppingMall.order.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class OrderItemDto implements Serializable {

    @Serial //클래스 버전 관리를 위해
    private static final long serialVersionUID = 1L;    //고유 식별자 필드

    private Long productId;
    private String productName;
    @Min(value = 1, message = "주문 수량은 최소 1개 이상이어야 합니다.")
    private int quantity;
    private int price;


}
