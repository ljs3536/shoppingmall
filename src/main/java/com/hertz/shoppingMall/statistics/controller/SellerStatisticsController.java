package com.hertz.shoppingMall.statistics.controller;

import com.hertz.shoppingMall.statistics.service.RecommendInfoService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/stats/best/{sellerId}")
    @ResponseBody
    public Mono<Object> getMoreSelling(@PathVariable("sellerId") String sellerId) {
        return recommendInfoService.getMoreSellingProducts(sellerId);
    }

    @GetMapping("/stats/popular/{sellerId}")
    @ResponseBody
    public List<Map<String, Object>> getPopular(@PathVariable("sellerId") String sellerId) {
        return (List<Map<String, Object>>) recommendInfoService.getPopularProductsByCategory(sellerId).block();
    }

    @GetMapping("/stats/addedCart/{sellerId}")
    @ResponseBody
    public List<Map<String, Object>> getAddedCart(@PathVariable("sellerId") String sellerId) {
        return (List<Map<String, Object>>) recommendInfoService.getAddedCartProducts(sellerId).block();
    }

    @GetMapping("/stats/highrated/{sellerId}")
    @ResponseBody
    public List<Map<String, Object>> getHighRated(@PathVariable("sellerId") String sellerId) {
        return (List<Map<String, Object>>) recommendInfoService.getHighRatedProducts(sellerId).block();
    }

    @GetMapping("/stats/trending/{sellerId}")
    @ResponseBody
    public List<Map<String, Object>> getTrending(@PathVariable("sellerId") String sellerId) {
        return (List<Map<String, Object>>) recommendInfoService.getTrendingProducts(sellerId).block();
    }
}
