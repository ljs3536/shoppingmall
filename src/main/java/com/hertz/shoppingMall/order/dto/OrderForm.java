package com.hertz.shoppingMall.order.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderForm {

    private List<OrderItemDto> orderItems = new ArrayList<>();
    private String recipient;
    private String phoneNumber;
    private String address;
    private String orderRequest;
}
