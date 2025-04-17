package com.hertz.shoppingMall.statistics.service;

import com.hertz.shoppingMall.config.security.CustomUserDetails;
import com.hertz.shoppingMall.ml.model.ModelType;
import com.hertz.shoppingMall.ml.service.MLModelService;
import com.hertz.shoppingMall.product.component.ProductConverter;
import com.hertz.shoppingMall.product.dto.ProductDto;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecommendFacadeService {

    private final RecommendationService recommendationService;
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final RecommendInfoService recommendInfoService;
    private final MLModelService mlModelService;

    @Transactional
    public List<ProductDto> getRecommendProducts(CustomUserDetails userDetails){

        String activeModel = mlModelService.getActiveModel(ModelType.RECOMMEND);
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("userId", userDetails.getLoginId()); // 또는 memberId
        userInfo.put("age", userDetails.getAge());
        userInfo.put("gender", userDetails.getGender());
        userInfo.put("region", userDetails.getRegion());

        List<String> recommendedProductNames = recommendationService.getRecommendedProducts(userInfo, activeModel).block();

        List<Product> recommendedProducts = productService.getProductsByNames(recommendedProductNames);

        return productConverter.convertToDtoList(recommendedProducts);
    }
    @Transactional(readOnly = true)
    public List<ProductDto> getHighRatedProducts(){
        List<String> productNames = extractNamesFromResultFromMono(recommendInfoService.getHighRatedProducts(null));
        List<Product> products = productService.getProductsByNames(productNames);
        return productConverter.convertToDtoList(products);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getTrendingProducts(){
        List<String> productNames = extractNamesFromResultFromMono(recommendInfoService.getTrendingProducts(null));
        List<Product> products = productService.getProductsByNames(productNames);
        return productConverter.convertToDtoList(products);
    }

    private List<String> extractNamesFromResultFromMono(Mono<Object> resultMono) {
        Object result = resultMono.block();
        if (result instanceof List<?> list) {
            return list.stream()
                    .filter(item -> item instanceof Map)
                    .map(item -> ((Map<?, ?>) item).get("name"))
                    .filter(String.class::isInstance)
                    .map(String.class::cast)
                    .toList();
        }
        return List.of();
    }
}
