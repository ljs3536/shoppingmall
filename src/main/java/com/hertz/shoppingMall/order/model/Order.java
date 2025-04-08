package com.hertz.shoppingMall.order.model;

import com.hertz.shoppingMall.config.jpa.BaseDateEntity;
import com.hertz.shoppingMall.member.model.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders") // order는 SQL예약어로 테이블 이름 수정
public class Order extends BaseDateEntity implements Serializable {

    @Serial //클래스 버전 관리를 위해
    private static final long serialVersionUID = 2L;    //고유 식별자 필드

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    //주문자 정보
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 주문 상품 정보
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private String orderNumber; // 주문번호

    // 총 결제 금액
    private int totalPrice;

    //수령인
    private String recipient;
    // 배송 주소
    private String address;
    // 배송자 연락처
    private String phoneNumber;
    // 주문 요청사항
    private String orderRequest;
    // 배송비
    private int shoppingFee;

    private String orderType;

    // 연관관계 편의 메서드
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // 총 가격 계산 메서드
    public void calculateTotalPrice() {
        this.totalPrice = orderItems.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum() + this.shoppingFee;
    }
}
