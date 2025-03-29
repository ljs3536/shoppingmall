package com.hertz.shoppingMall.cart.repository;

import com.hertz.shoppingMall.cart.model.Cart;
import com.hertz.shoppingMall.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByMember(Member member);
}
