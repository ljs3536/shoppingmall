package com.hertz.shoppingMall.order.controller;

import com.hertz.shoppingMall.config.security.CustomUserDetails;
import com.hertz.shoppingMall.order.dto.OrderDetailDto;
import com.hertz.shoppingMall.order.dto.OrderStatusUpdateDto;
import com.hertz.shoppingMall.order.model.Order;
import com.hertz.shoppingMall.order.model.OrderItem;
import com.hertz.shoppingMall.order.model.OrderStatus;
import com.hertz.shoppingMall.order.service.OrderItemService;
import com.hertz.shoppingMall.order.service.OrderService;
import com.hertz.shoppingMall.utils.page.PageRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/seller/order")
@RequiredArgsConstructor
@Slf4j
public class SellerOrderController {

    private final OrderService orderService;

    private final OrderItemService orderItemService;

    @GetMapping("/list")
    public String list(@AuthenticationPrincipal CustomUserDetails userDetails
            , PageRequestDto pageRequestDto
            , Model model){
        //List<OrderItem> orderItems = orderItemService.getOrderItemListBySellerId(userDetails.getMemberId());
        Page<OrderItem> orderItems = orderItemService.getOrderItemListBySellerId(pageRequestDto, userDetails.getMemberId());
        model.addAttribute("orderItems", orderItems.getContent());
        model.addAttribute("orderItemPage", orderItems);
        return "order/sellerOrderList";
    }

    @PostMapping("/updateStatus")
    @ResponseBody
    public ResponseEntity<?> updateOrderStatus(@RequestBody OrderStatusUpdateDto dto){
        try{
            orderItemService.updateOrderStatus(dto.getOrderItemId(), dto.getStatus());
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(Collections.singletonMap("message",e.getMessage()));
        }
    }

    @GetMapping("/detail/{orderId}")
    @ResponseBody
    public ResponseEntity<?> getOrderDetail(@PathVariable("orderId")Long orderId){
        OrderDetailDto orderDetail = orderService.getOrderDetail(orderId);
        if(orderDetail != null){
            return ResponseEntity.ok(orderDetail);
        } else{
            return ResponseEntity.badRequest().body(Collections.singletonMap("message","주문 정보를 찾을 수 없습니다."));
        }
    }

}
