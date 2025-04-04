package com.hertz.shoppingMall.order.service;

import com.hertz.shoppingMall.cart.service.CartItemService;
import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.order.dto.OrderForm;
import com.hertz.shoppingMall.order.dto.OrderItemDto;
import com.hertz.shoppingMall.order.model.Order;
import com.hertz.shoppingMall.order.model.OrderItem;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.service.ProductService;
import com.hertz.shoppingMall.utils.log.event.model.OrderCompletedEvent;
import com.hertz.shoppingMall.utils.log.model.OrderLog;
import com.hertz.shoppingMall.utils.log.repository.OrderLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderFacadeService {

    private final OrderService orderService;
    private final ProductService productService;
    private final CartItemService cartItemService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Order saveOrder(OrderForm form, Member member){
        // Order 생성 로직
        Order order = new Order();
        for (OrderItemDto orderItemDto : form.getOrderItems()) {
            Product product = productService.getProduct(orderItemDto.getProductId());

            OrderItem orderItem = OrderItem.createOrderItem(product, orderItemDto.getQuantity());
            order.addOrderItem(orderItem);
        }

        // 주문 정보 설정
        order.setRecipient(form.getRecipient());
        order.setPhoneNumber(form.getPhoneNumber());
        order.setAddress(form.getAddress());
        order.setOrderRequest(form.getOrderRequest());

        // 멤버 설정
        order.setMember(member);

        if(form.isFormCart()){
            List<Long> productIds = form.getOrderItems().stream()
                    .map(OrderItemDto::getProductId)
                    .collect(Collectors.toList());

            cartItemService.removeCartItemsByProductIds(member.getId(), productIds);
        }
        orderService.saveOrder(order);

        applicationEventPublisher.publishEvent(new OrderCompletedEvent(order, member));
        return order;
    }
}
