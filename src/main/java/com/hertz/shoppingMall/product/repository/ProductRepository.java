package com.hertz.shoppingMall.product.repository;


import com.hertz.shoppingMall.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
