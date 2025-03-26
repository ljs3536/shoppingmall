package com.hertz.shoppingMall.order.service;

import com.hertz.shoppingMall.order.model.Order;
import com.hertz.shoppingMall.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    //주문 목록
    public List<Order> getOrderList(){
        return orderRepository.findAll();
    }

    //주문 등록
    public void saveOrder(Order order){
        orderRepository.save(order);
    }

    //주문 상세
    public Order getOrder(Long orderId){
        return orderRepository.findById(orderId).orElse(null);
    }


}
