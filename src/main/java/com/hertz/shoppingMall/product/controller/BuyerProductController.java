package com.hertz.shoppingMall.product.controller;

import com.hertz.shoppingMall.product.component.ProductConverter;
import com.hertz.shoppingMall.product.dto.ProductForm;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.service.ProductService;
import com.hertz.shoppingMall.utils.page.PageRequestDto;
import com.hertz.shoppingMall.utils.search.SearchRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/buyer/products")
@Slf4j
@RequiredArgsConstructor
public class BuyerProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;

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
    public String view(@PathVariable("productId")Long productId, Model model){
        Product product = productService.getProduct(productId);
        ProductForm productForm = productConverter.convertToForm(product);
        model.addAttribute("product", productForm);
        return "/products/productView";
    }

}
