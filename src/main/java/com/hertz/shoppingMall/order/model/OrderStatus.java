package com.hertz.shoppingMall.order.model;

public enum OrderStatus {
    PENDING,       // 주문 대기
    PROCESSING,    // 처리 중
    SHIPPED,       // 배송 중
    DELIVERED,     // 배송 완료
    CANCELLED      // 주문 취소
}