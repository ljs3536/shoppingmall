package com.hertz.shoppingMall.cart.model;

import com.hertz.shoppingMall.config.jpa.BaseDateEntity;
import com.hertz.shoppingMall.product.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class CartItem extends BaseDateEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    // 총 가격 계산 메서드
    public int getTotalPrice() {
        return product.getPrice() * quantity;
    }

    // 수량 증가 메서드
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
}