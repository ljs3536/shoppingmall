package com.hertz.shoppingMall.order.repository;

import com.hertz.shoppingMall.order.model.Order;
import com.hertz.shoppingMall.order.model.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

    // 데이터 조회용 쿼리
    @EntityGraph(attributePaths = {"order", "product"})
    Page<OrderItem> findBySellerId(Pageable pageable, Long sellerId);

}
