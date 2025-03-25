package com.hertz.shoppingMall.product.controller;

import com.hertz.shoppingMall.product.dto.ProductForm;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller("/seller/products")
@Slf4j
@RequiredArgsConstructor
public class SellerProductController {

    private final ProductService productService;

    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("form", new Product());
        return "products/createProductForm";
    }

    @PostMapping("/new")
    public String create(ProductForm form){
        Product product = new Product();

        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setStockQuantity(form.getStockQuantity());
        product.setDescription(form.getDescription());

        productService.saveProduct(product);

        return "redirect:/";
    }

    @GetMapping("/list")
    public String list(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        List<Product> products = productService.findProductAll();
        model.addAttribute("products",products);
        return "products/productList";
    }

    @GetMapping("/{productId}/edit")
    public String updateProductForm(@PathVariable("productId")Long productId, Model model){
        Product product = productService.findOne(productId);

        ProductForm productForm = new ProductForm();
        productForm.setName(product.getName());
        productForm.setPrice(product.getPrice());
        productForm.setStockQuantity(product.getStockQuantity());
        productForm.setDescription(product.getDescription());

        model.addAttribute("form",productForm);
        return "products/updateProductForm";
    }

    @PostMapping("/{productId}/edit")
    public String updateProduct(@ModelAttribute("form")ProductForm form){

        productService.updateProduct(form.getId(), form.getName(), form.getPrice(), form.getStockQuantity(), form.getDescription());
        return "redirect:/products";
    }
}
