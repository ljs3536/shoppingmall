package com.hertz.shoppingMall.order.repository;

import com.hertz.shoppingMall.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
