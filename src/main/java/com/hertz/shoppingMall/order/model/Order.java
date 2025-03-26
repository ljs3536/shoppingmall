package com.hertz.shoppingMall.order.model;

import com.hertz.shoppingMall.config.jpa.BaseDateEntity;
import com.hertz.shoppingMall.member.model.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Order extends BaseDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //주문자 정보
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 주문 상품 정보
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    private String orderNumber; // 주문번호
    // 주문 상태 정보
    private String status;
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

}
