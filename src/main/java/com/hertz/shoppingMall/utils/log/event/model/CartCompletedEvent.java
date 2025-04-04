package com.hertz.shoppingMall.utils.log.event.model;

import com.hertz.shoppingMall.cart.model.CartItem;
import com.hertz.shoppingMall.member.model.Member;
import lombok.Getter;

@Getter
public class CartCompletedEvent {

    private final CartItem cartItem;
    private final Member member;
    private final String actionType;    // "ADD" or "UPDATE"

    public CartCompletedEvent(CartItem cartItem, Member member,String actionType) {
        this.cartItem = cartItem;
        this.member = member;
        this.actionType = actionType;
    }
}
