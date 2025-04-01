package com.hertz.shoppingMall.order.dto;

import com.hertz.shoppingMall.order.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusUpdateDto {

    private Long orderItemId;
    private OrderStatus status;
}
