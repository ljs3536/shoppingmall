package com.hertz.shoppingMall.order.controller;

import com.hertz.shoppingMall.config.security.CustomUserDetails;
import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.order.dto.OrderForm;
import com.hertz.shoppingMall.order.dto.OrderItemDto;
import com.hertz.shoppingMall.order.model.Order;
import com.hertz.shoppingMall.order.model.OrderItem;
import com.hertz.shoppingMall.order.service.OrderService;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.service.ProductService;
import com.hertz.shoppingMall.utils.exception.custom.NotEnoughStockException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/buyer/order")
@Slf4j
@RequiredArgsConstructor
public class BuyerOrderController {

    private final ProductService productService;
    private final OrderService orderService;

    //주문 목록
    @GetMapping("/list")
    public String list(@AuthenticationPrincipal CustomUserDetails userDetails
            ,Model model){
        List<Order> orders = orderService.getOrderListByMemberId(userDetails.getMemberId());
        model.addAttribute("orders", orders);
        return "order/orderList";
    }
    //주문 등록
    @PostMapping("/new/{productId}")
    public String orderForm(@PathVariable("productId") Long productId
            , @RequestParam(name = "quantity",defaultValue = "1") int quantity
            , Model model){
        Product product = productService.getProduct(productId);

        OrderItemDto itemDto = new OrderItemDto();
        itemDto.setProductId(product.getId());
        itemDto.setProductName(product.getName());
        itemDto.setQuantity(quantity);
        itemDto.setPrice(product.getPrice());

        OrderForm orderForm = new OrderForm();
        orderForm.getOrderItems().add(itemDto);

        model.addAttribute("orderForm", orderForm);
        model.addAttribute("productId", productId);
        return "order/createOrderForm";
    }

    @PostMapping("/new/orders")
    public String createOrder(@Valid OrderForm form
            , BindingResult result
            , Model model
            ,@AuthenticationPrincipal CustomUserDetails userDetails){
        Long productId = form.getProductId();
        if (result.hasErrors()) {
            // 에러 발생 시 다시 주문 폼으로 돌아가면서 기존 정보 유지

            model.addAttribute("orderForm", form);
            model.addAttribute("productId", productId);
            return "order/createOrderForm";
        }
        try {
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
                Member member = new Member();
                member.setId(userDetails.getMemberId());
                order.setMember(member);

                // 주문 저장 및 재고 처리
                Order savedOrder = orderService.saveOrder(order);

                return "redirect:/buyer/order/view/" + savedOrder.getId();

        } catch (NotEnoughStockException e) {
            // 재고 부족 시 에러 처리
            result.rejectValue("orderItems", "error.stockShortage", e.getMessage());
            model.addAttribute("orderForm", form);
            model.addAttribute("productId", form.getProductId());
            return "order/createOrderForm";
        }
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
    @GetMapping("/view/{orderId}")
    public String view(@PathVariable("orderId")Long orderid, Model model){
        Order order = orderService.getOrder(orderid);
        model.addAttribute("order", order);
        return "order/orderView";
    }

    //주문 수정

    //주문 취소

}
