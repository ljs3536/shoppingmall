package com.hertz.shoppingMall.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDto {

    //수령인
    private String recipient;
    // 배송 주소
    private String address;
    // 배송자 연락처
    private String phoneNumber;
    // 주문 요청사항
    private String orderRequest;

    public OrderDetailDto(String recipient, String address, String phoneNumber, String orderRequest) {
        this.recipient = recipient;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.orderRequest = orderRequest;
    }
}
