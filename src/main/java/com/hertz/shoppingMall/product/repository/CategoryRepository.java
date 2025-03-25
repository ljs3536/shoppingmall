package com.hertz.shoppingMall.product.repository;

import com.hertz.shoppingMall.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
