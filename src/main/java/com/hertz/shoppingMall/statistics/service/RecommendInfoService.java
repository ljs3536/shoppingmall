package com.hertz.shoppingMall.statistics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecommendInfoService {

    private final WebClient webClient;

    public Mono<Object> getMoreSellingProducts(String sellerId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/products/moreSelling")
                        .queryParamIfPresent("sellerId", Optional.ofNullable(sellerId))
                        .build())
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Object> getPopularProductsByCategory(String sellerId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/products/popularByCategory")
                        .queryParamIfPresent("sellerId", Optional.ofNullable(sellerId))
                        .build())
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Object> getAddedCartProducts(String sellerId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/products/addedCart")
                        .queryParamIfPresent("sellerId", Optional.ofNullable(sellerId))
                        .build())
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Object> getHighRatedProducts(String sellerId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/products/highRated")
                        .queryParamIfPresent("sellerId", Optional.ofNullable(sellerId))
                        .build())
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Object> getTrendingProducts(String sellerId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/products/trending")
                        .queryParamIfPresent("sellerId", Optional.ofNullable(sellerId))
                        .build())
                .retrieve()
                .bodyToMono(Object.class);
    }
}
