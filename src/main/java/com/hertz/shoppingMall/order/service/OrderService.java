package com.hertz.shoppingMall.order.service;

import com.hertz.shoppingMall.order.dto.OrderDetailDto;
import com.hertz.shoppingMall.order.model.Order;
import com.hertz.shoppingMall.order.model.OrderItem;
import com.hertz.shoppingMall.order.repository.OrderRepository;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.repository.ProductRepository;
import com.hertz.shoppingMall.utils.exception.custom.NotEnoughStockException;
import com.hertz.shoppingMall.utils.page.PageRequestDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.hertz.shoppingMall.order.model.OrderStatus.PROCESSING;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // 구매자의 주문 목록
    public Page<Order> getOrderListByMemberId(PageRequestDto pageRequestDto, Long memberId){
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize(), Sort.by(pageRequestDto.getSort()).descending());
        return orderRepository.findByMemberIdOrderByCreatedDateTimeDesc(pageable, memberId);
    }

    //주문 등록
    @Transactional
    public Order saveOrder(Order order){

        // 주문 아이템별 재고 처리
        for (OrderItem orderItem : order.getOrderItems()) {
            Product product = productRepository.findById(orderItem.getProduct().getId())
                    .orElseThrow(() -> new EntityNotFoundException("상품을 찾을 수 없습니다."));

            // 재고 검증 및 감소
            if (product.getStockQuantity() < orderItem.getQuantity()) {
                throw new NotEnoughStockException(product.getName() + " 상품의 재고가 부족합니다.");
            }

            // 재고 감소
            product.removeStock(orderItem.getQuantity());
        }
        // 총 주문 가격 다시 한 번 계산 (보안을 위해)
        order.calculateTotalPrice();

        return orderRepository.save(order);
    }

    //주문 상세
    public Order getOrder(Long orderId){
        return orderRepository.findById(orderId).orElse(null);
    }

    // 배송 정보
    public OrderDetailDto getOrderDetail(Long orderId) {
        return orderRepository.findById(orderId)
                .map(order -> new OrderDetailDto(
                        order.getRecipient(),
                        order.getAddress(),
                        order.getPhoneNumber(),
                        order.getOrderRequest()
                )).orElse(null);
    }
}
