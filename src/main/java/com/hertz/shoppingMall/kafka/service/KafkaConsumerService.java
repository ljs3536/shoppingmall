package com.hertz.shoppingMall.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "user-activity", groupId = "my-consumer-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        // TODO: Elasticsearch 저장 등 처리
    }
}