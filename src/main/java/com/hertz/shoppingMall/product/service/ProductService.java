package com.hertz.shoppingMall.product.service;

import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void saveProduct(Product product){
        productRepository.save(product);
    }

    public void updateProduct(Long productId, String name, int price, int stockQuantity, String description){
        Product findProduct = productRepository.findById(productId).orElse(null);
        if(findProduct != null){
            findProduct.setPrice(price);
            findProduct.setName(name);
            findProduct.setStockQuantity(stockQuantity);
            findProduct.setDescription(description);
        }
    }

    public List<Product> findProductAll(){
        return productRepository.findAll();
    }

    public Product findOne(Long productId){
        return productRepository.findById(productId).orElse(null);
    }

    public List<Product> findProductListBySeller(Long memberId){
        return productRepository.findByCreatedBy(memberId);
    }
}
