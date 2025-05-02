package com.hertz.shoppingMall.utils.kafka.service;

import com.hertz.shoppingMall.ml.repository.MLModelRepository;
import com.hertz.shoppingMall.ml.repository.ModelTrainLogRepository;
import com.hertz.shoppingMall.ml.service.ModelTrainLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final ModelTrainLogService modelTrainLogService;

    @KafkaListener(topics = "user-activity", groupId = "my-consumer-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        // TODO: Elasticsearch 저장 등 처리
    }

    @KafkaListener(topics = "model-train-result", groupId = "spring-consumer")
    public void listenModelResult(String message) {

        modelTrainLogService.setModelTrainResult(message);

    }
}