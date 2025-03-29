package com.hertz.shoppingMall.cart.controller;

import com.hertz.shoppingMall.cart.dto.CartForm;
import com.hertz.shoppingMall.cart.dto.CartItemDto;
import com.hertz.shoppingMall.cart.model.Cart;
import com.hertz.shoppingMall.cart.model.CartItem;
import com.hertz.shoppingMall.cart.service.CartService;
import com.hertz.shoppingMall.config.security.CustomUserDetails;
import com.hertz.shoppingMall.member.model.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/cart/list")
    public String list(@AuthenticationPrincipal CustomUserDetails userDetails, Model model){

        Long memberId = userDetails.getMemberId();
        Member member = new Member();
        member.setId(memberId);
        Cart cart = cartService.getCart(member);
        List<CartItemDto> cartItems = cart.getCartItems().stream()
                        .map(cartItem -> new CartItemDto(
                                cartItem.getId(),
                                cartItem.getProduct().getId(),
                                cartItem.getProduct().getName(),
                                cartItem.getQuantity(),
                                cartItem.getProduct().getPrice(),
                                cartItem.getTotalPrice()
                        )).toList();

        int totalCartPrice = cartItems.stream().mapToInt(CartItemDto::getTotalPrice).sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalCartPrice", totalCartPrice);
        return "cart/cartList";
    }

    @PostMapping("/cart/add")
    @ResponseBody
    public ResponseEntity<CartItemDto> addToCart(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody @Valid CartForm cartForm
    ) {
        Long memberId = userDetails.getMemberId();
        Member member = new Member();
        member.setId(memberId);

        try {
            // 장바구니에 상품 추가
            CartItem cartItem = cartService.addToCart(
                    member,
                    cartForm.getProductId(),
                    cartForm.getQuantity()
            );

            // 응답 DTO 생성
            CartItemDto responseDto = new CartItemDto(
                    cartItem.getId(),
                    cartItem.getProduct().getId(),
                    cartItem.getProduct().getName(),
                    cartItem.getQuantity(),
                    cartItem.getProduct().getPrice(),
                    cartItem.getTotalPrice()
            );

            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            log.error("장바구니 추가 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 장바구니 아이템 삭제
    @DeleteMapping("/cart/remove/{cartItemId}")
    @ResponseBody
    public ResponseEntity<Void> removeCartItem(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long cartItemId
    ) {
        Long memberId = userDetails.getMemberId();
        Member member = new Member();
        member.setId(memberId);
        try {
            // 장바구니 아이템 삭제
            cartService.removeCartItem(cartItemId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("장바구니 아이템 삭제 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
