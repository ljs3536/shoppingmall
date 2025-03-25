package com.hertz.shoppingMall.product.controller;

import com.hertz.shoppingMall.product.dto.ProductForm;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller("/buyer/products")
@Slf4j
@RequiredArgsConstructor
public class BuyerProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public String list(Model model){
        List<Product> products = productService.findProductAll();
        model.addAttribute("products",products);
        return "products/productList";
    }

    @GetMapping("/view")

    @PostMapping("products/{productId}/edit")
    public String updateProduct(@ModelAttribute("form")ProductForm form){

        productService.updateProduct(form.getId(), form.getName(), form.getPrice(), form.getStockQuantity(), form.getDescription());
        return "redirect:/products";
    }
}
