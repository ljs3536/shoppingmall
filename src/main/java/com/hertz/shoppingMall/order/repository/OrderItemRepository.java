package com.hertz.shoppingMall.order.repository;

import com.hertz.shoppingMall.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

    @Query("SELECT oi FROM OrderItem oi JOIN FETCH oi.order WHERE oi.sellerId = :sellerId")
    List<OrderItem> findBySellerId(@Param("sellerId") Long sellerId);
}
