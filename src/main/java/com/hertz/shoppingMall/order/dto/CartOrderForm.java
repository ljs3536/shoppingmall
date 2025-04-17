package com.hertz.shoppingMall.order.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartOrderForm {
    @NotEmpty(message = "상품 ID 목록은 비어 있을 수 없습니다.")
    @Size(min = 1, message = "최소 한 개의 상품이 필요합니다.")
    private List<Long> productId;

    @NotEmpty(message = "수량 목록은 비어 있을 수 없습니다.")
    private List<Integer> quantity;
}
