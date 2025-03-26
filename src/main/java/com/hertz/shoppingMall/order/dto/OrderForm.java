package com.hertz.shoppingMall.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderForm {

    private List<OrderItemDto> orderItems = new ArrayList<>();

    // 수령인
    @NotBlank(message = "수령인 정보는 필수입니다.")
    private String recipient;
    // 배송자 연락처
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "올바른 전화번호 형식이 아닙니다")
    private String phoneNumber;
    // 배송 주소
    @NotBlank(message = "배송 주소는 필수입니다.")
    private String address;
    // 주문 요청사항
    @Size(max = 500, message = "주문 요청사항은 500자를 초과할 수 없습니다")
    private String orderRequest;
}
