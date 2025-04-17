package com.hertz.shoppingMall.main.controller;

import com.hertz.shoppingMall.config.security.CustomUserDetails;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.service.ProductService;
import com.hertz.shoppingMall.statistics.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final RecommendationService recommendationService;
    private final ProductService productService;

    @RequestMapping("/")
    public String home(Model model,Principal principal){

        if (principal != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            // 권한 체크
            boolean isBuyer = authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_BUYER"));

            if (isBuyer) {
                Map<String, Object> userInfo = new HashMap<>();
                userInfo.put("userId", userDetails.getLoginId()); // 또는 memberId
                userInfo.put("age", userDetails.getAge());
                userInfo.put("gender", userDetails.getGender());
                userInfo.put("region", userDetails.getRegion());

                try {
                    List<String> recommendedProductNames = recommendationService.getRecommendedProducts(userInfo, "xgb_classifier").block();

                    List<Product> recommendedProducts = productService.getProductsByNames(recommendedProductNames);
                    model.addAttribute("recommendedProducts", recommendedProducts);
                }catch (Exception e){
                    model.addAttribute("recommendedProducts", null);
                }

            }
        }
        return "main";
    }
}
