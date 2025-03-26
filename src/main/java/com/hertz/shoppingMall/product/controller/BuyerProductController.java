package com.hertz.shoppingMall.product.controller;

import com.hertz.shoppingMall.product.dto.ProductForm;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/buyer/products")
@Slf4j
@RequiredArgsConstructor
public class BuyerProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public String list(Model model){
        List<Product> products = productService.getProductAll();
        model.addAttribute("products",products);
        return "products/productList";
    }

    @GetMapping("/view/{productId}")
    public String view(@PathVariable("productId")Long productId, Model model){
        Product product = productService.getProduct(productId);
        model.addAttribute("product", product);
        return "/products/productView";
    }

}
