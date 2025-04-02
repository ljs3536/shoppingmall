package com.hertz.shoppingMall.cart.repository;

import com.hertz.shoppingMall.cart.model.Cart;
import com.hertz.shoppingMall.cart.model.CartItem;
import com.hertz.shoppingMall.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

    @Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.cart.member.id = :memberId AND ci.id = :cartItemId")
    void deleteByMemberIdAndCartItemId(@Param("memberId") Long memberId, @Param("cartItemId") Long cartItemId);

    @Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.cart.member.id = :memberId AND ci.product.id IN :productIds")
    void deleteByMemberIdAndProductIdIn(@Param("memberId") Long memberId, @Param("productIds") List<Long> productIds);

}
