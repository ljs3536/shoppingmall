package com.hertz.shoppingMall.product.repository;


import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    List<Product> findByIdIn(List<Long> productIds);
}
