package com.hertz.shoppingMall.product.service;

import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional  // 변경감지(dirty checking)
    public void saveProduct(Product product){
        //java.lang.IllegalStateException: 이미 존재하는 회원입니다. 에러 발생 시 처리 필요
        productRepository.save(product);
    }

    @Transactional  // 변경감지(dirty checking)
    public void updateProduct(Long productId, String name, int price, int stockQuantity, String description, Long memberId){
        Product findProduct = productRepository.findById(productId).orElse(null);
        if(findProduct != null){
            Member modifiedBy = new Member();
            modifiedBy.setId(memberId);
            findProduct.setPrice(price);
            findProduct.setName(name);
            findProduct.setStockQuantity(stockQuantity);
            findProduct.setDescription(description);
            findProduct.setModifiedBy(modifiedBy);
        }
    }

    public List<Product> findProductAll(){
        return productRepository.findAll();
    }

    public Product findOne(Long productId){
        return productRepository.findById(productId).orElse(null);
    }

    public List<Product> findProductListBySeller(Long memberId){
        Member member = new Member();
        member.setId(memberId);
        return productRepository.findByCreatedBy(member);
    }
}
