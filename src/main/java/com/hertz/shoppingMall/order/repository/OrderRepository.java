package com.hertz.shoppingMall.order.repository;

import com.hertz.shoppingMall.order.model.Order;
import com.hertz.shoppingMall.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findTopByMemberIdOrderByCreatedDateTimeDesc(Long memberId);
    List<Order> findByMemberIdOrderByCreatedDateTimeDesc(Long memberId);
}
