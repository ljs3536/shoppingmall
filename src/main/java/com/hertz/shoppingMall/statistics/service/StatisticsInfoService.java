package com.hertz.shoppingMall.statistics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StatisticsInfoService {

    private final WebClient webClient;

    public Mono<Object> getAgeGroupFavorites() {
        return webClient.get()
                .uri("/search/products/age")
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Object> getRegionFavorites() {
        return webClient.get()
                .uri("/search/products/region")
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Object> getMonthlyTrends() {
        return webClient.get()
                .uri("/search/products/trend")
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Object> getYearlySales(String year) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/products/years")
                        .queryParam("year", year)
                        .build())
                .retrieve()
                .bodyToMono(Object.class);
    }

}
