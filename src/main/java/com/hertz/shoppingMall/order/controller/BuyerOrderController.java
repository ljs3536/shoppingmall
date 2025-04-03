package com.hertz.shoppingMall.order.controller;

import com.hertz.shoppingMall.config.security.CustomUserDetails;
import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.order.dto.CartOrderForm;
import com.hertz.shoppingMall.order.dto.OrderForm;
import com.hertz.shoppingMall.order.dto.OrderItemDto;
import com.hertz.shoppingMall.order.model.Order;
import com.hertz.shoppingMall.order.model.OrderItem;
import com.hertz.shoppingMall.order.service.OrderFacadeService;
import com.hertz.shoppingMall.order.service.OrderService;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.service.ProductService;
import com.hertz.shoppingMall.utils.exception.custom.NotEnoughStockException;
import com.hertz.shoppingMall.utils.page.PageRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/buyer/order")
@Slf4j
@RequiredArgsConstructor
public class BuyerOrderController {

    private final ProductService productService;
    private final OrderService orderService;
    private final OrderFacadeService orderFacadeService;

    //주문 목록
    @GetMapping("/list")
    public String list(@AuthenticationPrincipal CustomUserDetails userDetail
            , PageRequestDto pageRequestDto
            , Model model){
        //List<Order> orders = orderService.getOrderListByMemberId(userDetails.getMemberId());
        Page<Order> orders = orderService.getOrderListByMemberId(pageRequestDto, userDetail.getMemberId());
        model.addAttribute("orders", orders.getContent());
        model.addAttribute("orderPage", orders);
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
        orderForm.setFormCart(false);
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
                Member member = new Member();
                member.setId(userDetails.getMemberId());

                Order savedOrder = orderFacadeService.saveOrder(form, member);

                return "redirect:/buyer/order/view/" + savedOrder.getId();

        } catch (NotEnoughStockException e) {
            // 재고 부족 시 에러 처리
            result.rejectValue("orderItems", "error.stockShortage", e.getMessage());
            model.addAttribute("orderForm", form);
            model.addAttribute("productId", form.getProductId());
            return "order/createOrderForm";
        }
    }

    @PostMapping("/new/cartOrders")
    public String orderFromCart(@ModelAttribute @Valid CartOrderForm cartForm
            , BindingResult result
            , Model model) {

        if (result.hasErrors()) {
            return "redirect:/cart/list"; // 유효성 검사 실패 시 다시 장바구니 페이지로 이동
        }

        List<Product> products = productService.getProductsByIds(cartForm.getProductId()); // 개선된 상품 조회 방식
        OrderForm orderForm = new OrderForm();

        for (int i = 0; i < cartForm.getProductId().size(); i++) {

            Long productId = cartForm.getProductId().get(i);
            Integer quantity = cartForm.getQuantity().get(i);
            if(productId != null) {
                // 제품 리스트에서 해당 상품 찾기 (Optional 활용)
                Product product = products.stream()
                        .filter(p -> p.getId().equals(productId))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("잘못된 상품 ID: " + productId));

                OrderItemDto itemDto = new OrderItemDto();
                itemDto.setProductId(product.getId());
                itemDto.setProductName(product.getName());
                itemDto.setQuantity(quantity);
                itemDto.setPrice(product.getPrice());
                orderForm.getOrderItems().add(itemDto);
            }
        }

        orderForm.setFormCart(true);
        model.addAttribute("orderForm", orderForm);
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
