package com.hertz.shoppingMall.product.model;


import com.hertz.shoppingMall.config.jpa.BaseDateEntity;
import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.utils.exception.custom.NotEnoughStockException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends BaseDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @NotEmpty
    private String name;
    private String description;
    private int price;
    private int stockQuantity;

    @ManyToOne
    @JoinColumn(name = "category_id")  // 하나의 상품은 하나의 카테고리에 속함
    private Category category;

    @ManyToOne
    @JoinColumn(name = "created_by")  // 상품을 등록한 회원
    private Member createdBy;

    @ManyToOne
    @JoinColumn(name = "modified_by") // 마지막으로 수정한 회원
    private Member modifiedBy;

    // 재고 감소 메서드
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
