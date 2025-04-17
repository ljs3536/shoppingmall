package com.hertz.shoppingMall.cart.service;

import com.hertz.shoppingMall.cart.model.Cart;
import com.hertz.shoppingMall.cart.model.CartItem;
import com.hertz.shoppingMall.cart.repository.CartItemRepository;
import com.hertz.shoppingMall.cart.repository.CartRepository;
import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.repository.ProductRepository;
import com.hertz.shoppingMall.utils.log.event.model.CartCompletedEvent;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public Cart getCart(Member member){
        return cartRepository.findByMember(member)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setMember(member);
                    return cartRepository.save(newCart);
                });
    }

    // 장바구니에 상품 추가
    @Transactional
    public CartItem addToCart(Member member, Long productId, int quantity) {
        // 회원의 장바구니 조회 또는 생성
        Cart cart = cartRepository.findByMember(member).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setMember(member);
            return cartRepository.save(newCart);
        });

        // 상품 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        // 이미 장바구니에 있는 상품인지 확인
        Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);

        CartItem cartItem;
        String actionType;

        if (existingCartItem.isPresent()) {
            // 이미 있다면 수량 추가
            cartItem = existingCartItem.get();
            cartItem.addQuantity(quantity);
            actionType = "UPDATE";
        } else {
            // 새 CartItem 생성
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cart.addCartItem(cartItem);
            actionType = "ADD";
        }
        applicationEventPublisher.publishEvent(new CartCompletedEvent(cartItem, member, actionType));
        return cartItemRepository.save(cartItem);
    }

}
