package com.hertz.shoppingMall.statistics.controller;

import com.hertz.shoppingMall.config.security.CustomUserDetails;
import com.hertz.shoppingMall.statistics.service.RecommendInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/statistics")
@RequiredArgsConstructor
public class SellerStatisticsController {

    private final RecommendInfoService recommendInfoService;

    @GetMapping("/sellingInfo")
    public String getStatisticsPage() {
        return "statistics/sellingInfo";
    }

    @GetMapping("/moreSelling")
    @ResponseBody
    public Mono<Object> moreSelling(@AuthenticationPrincipal CustomUserDetails user) {
        return recommendInfoService.getMoreSellingProducts(user.getLoginId());
    }

    @GetMapping("/popularByCategory")
    @ResponseBody
    public Mono<Object> popularByCategory(@AuthenticationPrincipal CustomUserDetails user) {
        return recommendInfoService.getPopularProductsByCategory(user.getLoginId());
    }

    @GetMapping("/addedCart")
    @ResponseBody
    public Mono<Object> addedCart(@AuthenticationPrincipal CustomUserDetails user) {
        return recommendInfoService.getAddedCartProducts(user.getLoginId());
    }

    @GetMapping("/highRated")
    @ResponseBody
    public Mono<Object> highRated(@AuthenticationPrincipal CustomUserDetails user) {
        return recommendInfoService.getHighRatedProducts(user.getLoginId());
    }

    @GetMapping("/trending")
    @ResponseBody
    public Mono<Object> trending(@AuthenticationPrincipal CustomUserDetails user) {
        return recommendInfoService.getTrendingProducts(user.getLoginId());
    }

}
