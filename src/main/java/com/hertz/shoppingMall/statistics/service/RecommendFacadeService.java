package com.hertz.shoppingMall.statistics.service;

import com.hertz.shoppingMall.config.security.CustomUserDetails;
import com.hertz.shoppingMall.product.component.ProductConverter;
import com.hertz.shoppingMall.product.dto.ProductDto;
import com.hertz.shoppingMall.product.dto.ProductForm;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecommendFacadeService {

    private final RecommendationService recommendationService;
    private final ProductService productService;
    private final ProductConverter productConverter;

    public List<ProductDto> getRecommendProducts(CustomUserDetails userDetails){

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("userId", userDetails.getLoginId()); // 또는 memberId
        userInfo.put("age", userDetails.getAge());
        userInfo.put("gender", userDetails.getGender());
        userInfo.put("region", userDetails.getRegion());

        List<String> recommendedProductNames = recommendationService.getRecommendedProducts(userInfo, "content").block();

        List<Product> recommendedProducts = productService.getProductsByNames(recommendedProductNames);

        return productConverter.convertToDtoList(recommendedProducts);
    }
}
