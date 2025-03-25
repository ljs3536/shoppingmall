package com.hertz.shoppingMall.product.dto;

import com.hertz.shoppingMall.product.model.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {

    private Long id;

    @NotEmpty(message = "상품 이름은 필수입니다.")
    private String name;

    private String description;
    private int price;
    private int stockQuantity;

    private Category category;

}
