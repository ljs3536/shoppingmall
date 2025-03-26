package com.hertz.shoppingMall.order.controller;

import com.hertz.shoppingMall.order.dto.OrderForm;
import com.hertz.shoppingMall.order.dto.OrderItemDto;
import com.hertz.shoppingMall.order.service.OrderService;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/buyer/order")
@Slf4j
@RequiredArgsConstructor
public class BuyerOrderController {

    private final ProductService productService;
    private final OrderService orderService;

    //주문 목록

    //주문 등록
    @GetMapping("/new/{productId}")
    public String orderForm(@PathVariable("productId") Long productId, Model model){
        Product product = productService.getProduct(productId);

        OrderItemDto itemDto = new OrderItemDto();
        itemDto.setProductId(product.getId());
        itemDto.setProductName(product.getName());
        itemDto.setQuantity(1);
        itemDto.setPrice(product.getPrice());

        OrderForm orderForm = new OrderForm();
        orderForm.getOrderItems().add(itemDto);

        model.addAttribute("orderForm", orderForm);
        return "order/createOrderForm";
    }

    @PostMapping("/new")
    public String createOrder(@ModelAttribute OrderForm form){


        return "order/resultOrderInfo";
    }

    @PostMapping("/new/cart")
    public String orderFromCart(Model model) {
//        List<CartItem> cartItems = cartService.getCartItems();
//
//        OrderForm orderForm = new OrderForm();
//        for (CartItem cartItem : cartItems) {
//            OrderItemDto itemDto = new OrderItemDto();
//            itemDto.setProductId(cartItem.getProduct().getId());
//            itemDto.setProductName(cartItem.getProduct().getName());
//            itemDto.setQuantity(cartItem.getQuantity());
//            itemDto.setPrice(cartItem.getProduct().getPrice());
//
//            orderForm.getOrderItems().add(itemDto);
//        }
//
//        model.addAttribute("orderForm", orderForm);
        return "order/createOrderForm";
    }

    //주문 상세

    //주문 수정

    //주문 취소

}
