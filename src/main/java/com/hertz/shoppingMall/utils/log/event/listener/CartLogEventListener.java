package com.hertz.shoppingMall.utils.log.event.listener;

import com.hertz.shoppingMall.cart.model.Cart;
import com.hertz.shoppingMall.cart.model.CartItem;
import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.utils.log.event.model.CartCompletedEvent;
import com.hertz.shoppingMall.utils.log.model.CartLog;
import com.hertz.shoppingMall.utils.log.repository.CartLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class CartLogEventListener {

    private final CartLogRepository cartLogRepository;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleCartCompleted(CartCompletedEvent event){
        CartItem cartItem = event.getCartItem();
        Member member = event.getMember();
        String actionType = event.getActionType();

        CartLog cartLog = CartLog.createCartLog(cartItem, member, actionType);

        try {
            cartLogRepository.save(cartLog);
        } catch (Exception e) {
            log.error("CartLog 저장 실패", e);
        }
    }
}
