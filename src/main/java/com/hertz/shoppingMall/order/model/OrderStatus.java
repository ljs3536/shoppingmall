package com.hertz.shoppingMall.order.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING("주문대기"),
    PROCESSING("처리중"),
    SHIPPED("배송중"),
    DELIVERED("배송완료"),
    REVIEWED("리뷰완료"),
    CANCELLED("주문취소");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }
}