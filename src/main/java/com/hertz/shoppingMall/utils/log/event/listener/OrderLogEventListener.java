package com.hertz.shoppingMall.utils.log.event.listener;

import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.order.model.Order;
import com.hertz.shoppingMall.utils.log.event.model.OrderCompletedEvent;
import com.hertz.shoppingMall.utils.log.model.OrderLog;
import com.hertz.shoppingMall.utils.log.repository.OrderLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderLogEventListener {

    private final OrderLogRepository orderLogRepository;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleOrderCompleted(OrderCompletedEvent event){
        Order order = event.getOrder();
        Member member = event.getMember();


        List<OrderLog> logs = order.getOrderItems().stream().map(item -> {
            return OrderLog.createOrderLog(member,item,order.getOrderType());
        }).collect(Collectors.toList());

        try {
            orderLogRepository.saveAll(logs);
        } catch (Exception e) {
            // 로그 저장 실패는 별도로 기록하고, 메인 로직에는 영향 X
            log.error("OrderLog 저장 실패", e);
        }
    }

}
