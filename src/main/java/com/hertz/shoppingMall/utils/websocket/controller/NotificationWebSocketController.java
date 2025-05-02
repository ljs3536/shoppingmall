package com.hertz.shoppingMall.utils.websocket.controller;

import com.hertz.shoppingMall.alarm.model.Alarm;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class NotificationWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendNotificationToMember(Alarm alarm){
        messagingTemplate.convertAndSend("/topic/notifications/"+alarm.getTargetMember().getId(), alarm);
    }

}
