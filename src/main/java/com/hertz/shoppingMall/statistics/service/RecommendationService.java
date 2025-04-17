package com.hertz.shoppingMall.statistics.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationService {

    private final WebClient webClient;

    public Mono<List<String>> getRecommendedProducts(Map<String, Object> userInfo, String algo) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/recommend/product")
                        .queryParam("algo", algo)
                        .build())
                .bodyValue(Map.of("user_info", userInfo))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .map(result -> (List<String>) result.get("recommended_products"));
    }

}
