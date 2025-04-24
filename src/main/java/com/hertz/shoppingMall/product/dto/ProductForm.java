package com.hertz.shoppingMall.product.dto;

import com.hertz.shoppingMall.product.model.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductForm implements Serializable {

    @Serial //클래스 버전 관리를 위해
    private static final long serialVersionUID = 1L;    //고유 식별자 필드
    private Long id;

    @NotEmpty(message = "상품 이름은 필수입니다.")
    private String name;

    private String description;
    private int price;
    private int stockQuantity;

    private Category category;

    private MultipartFile mainImage;  // 메인 이미지 (썸네일)
    private List<MultipartFile> subImages;  // 서브 이미지 리스트

    private String mainImageUrl;
    private List<String> subImageUrl = new ArrayList<>();

    private List<Long> subImageIds = new ArrayList<>();
    private List<Boolean> deleteSubImages = new ArrayList<>();
}
