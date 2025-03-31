package com.hertz.shoppingMall.product.controller;

import com.hertz.shoppingMall.config.security.CustomUserDetails;
import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.product.component.ProductConverter;
import com.hertz.shoppingMall.product.dto.ProductForm;
import com.hertz.shoppingMall.product.model.Category;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.service.CategoryService;
import com.hertz.shoppingMall.product.service.ProductService;
import com.hertz.shoppingMall.utils.exception.image.component.SaveImageUtil;
import com.hertz.shoppingMall.utils.exception.image.model.Image;
import com.hertz.shoppingMall.utils.exception.image.model.ImageType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/seller/products")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('SELLER')")
public class SellerProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    private final ProductConverter productConverter;

    @GetMapping("/new")
    public String createForm(Model model){

        List<Category> categoryList = categoryService.getCategoryListAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("form", new Product());
        return "products/createProductForm";
    }

    @PostMapping("/new")
    public String create(@AuthenticationPrincipal CustomUserDetails userDetails, ProductForm form) throws IOException {
        Product product = new Product();
        // 멤버 데이터 세팅
        Member member = new Member();
        Long memberId = userDetails.getMemberId();
        member.setId(memberId);

        // 상품 데이터 세팅
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setStockQuantity(form.getStockQuantity());
        product.setDescription(form.getDescription());
        product.setCategory(form.getCategory());
        product.setCreatedBy(member);
        product.setModifiedBy(member);

        //productService.saveProduct(product);
        productService.saveProductWithImages(product, form.getMainImage(), form.getSubImages());

        return "redirect:/";
    }

    @GetMapping("/mylist")
    public String list(@AuthenticationPrincipal CustomUserDetails userDetails, HttpServletRequest request,Model model){
        Long memberId = userDetails.getMemberId();

        List<Product> products = productService.getProductListBySeller(memberId);
        List<ProductForm> productForms = productConverter.convertToFormList(products);
        model.addAttribute("products", productForms);
        return "products/productList";
    }

    @GetMapping("/{productId}/edit")
    public String updateProductForm(@PathVariable("productId")Long productId, Model model){
        Product product = productService.getProduct(productId);

        ProductForm productForm = productConverter.convertToForm(product);

        List<Category> categoryList = categoryService.getCategoryListAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("form",productForm);
        return "products/updateProductForm";
    }

    @PostMapping("/{productId}/edit")
    public String updateProduct(@AuthenticationPrincipal CustomUserDetails userDetails, @ModelAttribute("form")ProductForm form) throws IOException {
        Long memberId = userDetails.getMemberId();
        productService.updateProduct(form, memberId);
        return "redirect:/seller/products/mylist";
    }
}
