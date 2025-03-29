package com.hertz.shoppingMall.cart.repository;

import com.hertz.shoppingMall.cart.model.Cart;
import com.hertz.shoppingMall.cart.model.CartItem;
import com.hertz.shoppingMall.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}
