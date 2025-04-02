package com.hertz.shoppingMall.cart.controller;

import com.hertz.shoppingMall.cart.dto.CartForm;
import com.hertz.shoppingMall.cart.dto.CartItemDto;
import com.hertz.shoppingMall.cart.dto.CartUpdateDto;
import com.hertz.shoppingMall.cart.model.Cart;
import com.hertz.shoppingMall.cart.model.CartItem;
import com.hertz.shoppingMall.cart.service.CartItemService;
import com.hertz.shoppingMall.cart.service.CartService;
import com.hertz.shoppingMall.config.security.CustomUserDetails;
import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.utils.exception.image.service.ImageService;
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
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/buyer/cart")
@Slf4j
@RequiredArgsConstructor
public class BuyerCartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final ImageService imageService;
    @GetMapping("/list")
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
                                cartItem.getTotalPrice(),
                                imageService.getImageUrl(cartItem.getProduct().getMainImage())
                        )).toList();

        int totalCartPrice = cartItems.stream().mapToInt(CartItemDto::getTotalPrice).sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalCartPrice", totalCartPrice);
        return "cart/cartList";
    }

    @PostMapping("/add")
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
                    cartItem.getTotalPrice(),
                    imageService.getImageUrl(cartItem.getProduct().getMainImage())
            );

            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            log.error("장바구니 추가 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<?> updateCartItemQuantity(@RequestBody CartUpdateDto dto) {
        try {
            int totalPrice = cartItemService.updateCartItemQuantity(dto.getCartItemId(), dto.getQuantity());
            return ResponseEntity.ok().body(Map.of(
                    "message", "수량 업데이트 성공",
                    "totalPrice", totalPrice));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "수량 업데이트 실패"));
        }
    }

    // 장바구니 아이템 삭제
    @DeleteMapping("/remove/{cartItemId}")
    @ResponseBody
    public ResponseEntity<?> removeCartItem(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable("cartItemId") Long cartItemId
    ) {
        Long memberId = userDetails.getMemberId();
        Member member = new Member();
        member.setId(memberId);
        try {
            // 장바구니 아이템 삭제
            cartItemService.removeCartItem(memberId, cartItemId);
            return ResponseEntity.ok().body(Map.of("message","장바구니 상품 삭제 성공"));
        } catch (Exception e) {
            log.error("장바구니 아이템 삭제 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
