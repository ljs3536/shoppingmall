package com.hertz.shoppingMall.cart.service;

import com.hertz.shoppingMall.cart.model.CartItem;
import com.hertz.shoppingMall.cart.repository.CartItemRepository;
import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.utils.log.event.model.CartCompletedEvent;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void removeCartItemsByProductIds(Long memberId, List<Long> productIds) {
        cartItemRepository.deleteByMemberIdAndProductIdIn(memberId, productIds);
    }

    // 장바구니에서 상품 제거
    @Transactional
    public void removeCartItem(Member member, Long cartItemId) {

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(()-> new EntityNotFoundException("CartItem not found"));
        cartItemRepository.deleteByMemberIdAndCartItemId(member.getId(), cartItemId);
        applicationEventPublisher.publishEvent(new CartCompletedEvent(cartItem, member, "REMOVE"));
    }

    @Transactional
    public int updateCartItemQuantity(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 장바구니에 없습니다."));;

        cartItem.setQuantity(quantity);
        return cartItem.getTotalPrice();
    }
}
