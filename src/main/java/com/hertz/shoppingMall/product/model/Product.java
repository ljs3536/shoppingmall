package com.hertz.shoppingMall.product.model;


import com.hertz.shoppingMall.config.jpa.BaseDateEntity;
import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.utils.exception.custom.NotEnoughStockException;
import com.hertz.shoppingMall.utils.image.model.Image;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class Product extends BaseDateEntity implements Serializable {

    @Serial //클래스 버전 관리를 위해
    private static final long serialVersionUID = 1L;    //고유 식별자 필드

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

    // 서브 이미지 리스트 (1:N 관계)
    @OneToMany
    @JoinColumn(name = "reference_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    private List<Image> subImages;

    // 재고 감소 메서드
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

    // 메인 이미지
    public Image getMainImage() {
        return subImages.stream()
                .filter(Image::isMain) // isMain이 true인 이미지 필터링
                .findFirst()
                .orElse(null); // 없으면 null 반환
    }
    // 서브 이미지
    public List<Image> getSubImage() {
        return subImages.stream()
                .filter(image -> !image.isMain()) // isMain이 true인 이미지 필터링
                .toList(); // 없으면 null 반환
    }

    public String getMainImageUrl() {
        Image mainImage = getMainImage();
        return (mainImage != null) ? mainImage.getFileName() : "/images/default.jpg";
    }
}
