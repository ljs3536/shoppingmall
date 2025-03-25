package com.hertz.shoppingMall.product.controller;

import com.hertz.shoppingMall.product.dto.CategoryForm;
import com.hertz.shoppingMall.product.dto.ProductForm;
import com.hertz.shoppingMall.product.model.Category;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.service.CategoryService;
import com.hertz.shoppingMall.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
@Slf4j
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/categoryList")
    public String list(Model model){
        List<Category> categoryList = categoryService.getCategoryListAll();
        model.addAttribute("categoryList",categoryList);
        return "products/categoryList";
    }

    @PostMapping("/category/new")
    @ResponseBody
    public ResponseEntity<Category> create(@RequestBody CategoryForm form){
        Category category = new Category();

        category.setName(form.getName());

        categoryService.saveCategory(category);

        return ResponseEntity.ok().body(category);
    }

    @PostMapping("/category/edit")
    @ResponseBody
    public ResponseEntity<?> updateProduct(@RequestBody CategoryForm form){

        categoryService.updateCategory(form.getId(), form.getName());
        return ResponseEntity.ok().body("{\"message\": \"Category updated\"}");
    }
}
