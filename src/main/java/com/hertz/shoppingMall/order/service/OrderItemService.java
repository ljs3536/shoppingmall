package com.hertz.shoppingMall.order.service;

import com.hertz.shoppingMall.order.model.Order;
import com.hertz.shoppingMall.order.model.OrderItem;
import com.hertz.shoppingMall.order.model.OrderStatus;
import com.hertz.shoppingMall.order.repository.OrderItemRepository;
import com.hertz.shoppingMall.utils.page.PageRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public List<OrderItem> getOrderItemListBySellerId(Long memberId) {
        return orderItemRepository.findBySellerId(memberId);
    }

    public Page<OrderItem> getOrderItemListBySellerId(PageRequestDto pageRequestDto, Long memberId) {
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize(), Sort.by(pageRequestDto.getSort()).descending());
        return orderItemRepository.findBySellerId(pageable, memberId);
    }
    @Transactional // 변경감지(dirty checking)
    public void updateOrderStatus(Long orderItemId, OrderStatus status) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElse(null);
        if(orderItem != null) {
            orderItem.setStatus(status);
        }else{
            throw new NoSuchElementException("해당 주문의 상품을 찾지 못했습니다.");
        }
    }

    public OrderItem getOrderItem(Long orderItemId) {
        return orderItemRepository.findById(orderItemId).orElseThrow();
    }

}
