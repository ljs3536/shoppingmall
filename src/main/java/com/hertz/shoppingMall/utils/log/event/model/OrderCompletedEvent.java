package com.hertz.shoppingMall.utils.log.event.model;

import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.order.model.Order;
import lombok.Getter;

@Getter
public class OrderCompletedEvent {

    private final Order order;
    private final Member member;

    public OrderCompletedEvent(Order order, Member member){
        this.order = order;
        this.member = member;
    }

}
