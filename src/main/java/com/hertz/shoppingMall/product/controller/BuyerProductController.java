package com.hertz.shoppingMall.product.controller;

import com.hertz.shoppingMall.product.component.ProductConverter;
import com.hertz.shoppingMall.product.dto.ProductForm;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.service.ProductService;
import com.hertz.shoppingMall.review.model.Review;
import com.hertz.shoppingMall.review.service.ReviewService;
import com.hertz.shoppingMall.utils.page.PageRequestDto;
import com.hertz.shoppingMall.utils.search.SearchRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/buyer/products")
@Slf4j
@RequiredArgsConstructor
public class BuyerProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ReviewService reviewService;

    @Value("${file.access-url}")
    private String accessUrl;

    @GetMapping("/list")
    public String list(@ModelAttribute SearchRequestDto searchRequestDto, PageRequestDto pageRequestDto, Model model){
        Page<Product> products = productService.getProductAll(searchRequestDto,pageRequestDto);
        Page<ProductForm> productForms = productConverter.convertToFormPage(products);
        model.addAttribute("products", productForms.getContent());
        model.addAttribute("productPage", productForms);
        return "products/productList";
    }

    @GetMapping("/view/{productId}")
    public String view(@PathVariable("productId")Long productId
            , @RequestParam(name = "page",defaultValue = "0") int page
            , Model model) throws UnsupportedEncodingException {
        Product product = productService.getProduct(productId);
        ProductForm productForm = productConverter.convertToForm(product);
        Page<Review> reviews = reviewService.getReviewsByProduct(productId, PageRequest.of(page, 5));
        model.addAttribute("product", productForm);
        model.addAttribute("reviews", reviews);
        return "/products/productView";
    }

}
