package com.hertz.shoppingMall.ml.service;

import com.hertz.shoppingMall.alarm.model.Alarm;
import com.hertz.shoppingMall.alarm.model.AlarmTarget;
import com.hertz.shoppingMall.alarm.repository.AlarmRepository;
import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.member.model.Role;
import com.hertz.shoppingMall.member.repository.MemberRepository;
import com.hertz.shoppingMall.ml.repository.ModelTrainLogRepository;
import com.hertz.shoppingMall.utils.websocket.controller.NotificationWebSocketController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModelTrainLogService {

    private final ModelTrainLogRepository modelTrainLogRepository;

    private final AlarmRepository alarmRepository;

    private final NotificationWebSocketController webSocketController;

    private final MemberRepository memberRepository;

    @Transactional
    public void setModelTrainResult(String message){

        JSONObject json = new JSONObject(message);
        Long logId = json.getLong("log_id"); // 💡 전달받은 log_id 사용
        String status = json.getString("status"); // 예: "success" or "fail"
        String algoName = json.getString("algo_name");
        String modelType = json.getString("model_type");

        log.info("모델 학습 결과 수신: {} [{}] - 상태: {}", algoName, modelType, status);

        modelTrainLogRepository.findById(logId).ifPresent(log -> {
            log.setSuccess("success".equalsIgnoreCase(status));
            log.setMessage("모델 학습 결과: " + status);
            log.setExecutedAt(LocalDateTime.now());
            modelTrainLogRepository.save(log);
        });

        List<Member> admins = memberRepository.findByRole(Role.ADMIN);

        List<Alarm> alarms = admins.stream().map(admin -> {
            Alarm alarm = new Alarm();
            alarm.setTitle("모델 학습 결과 도착");
            alarm.setContent(algoName + " [" + modelType + "] → " + status);
            alarm.setTargetMember(admin);
            alarm.setTarget(AlarmTarget.ADMIN);
            alarm.setCreatedAt(LocalDateTime.now());
            return alarm;
        }).collect(Collectors.toList());

        alarmRepository.saveAll(alarms);

//        webSocketController.sendNotificationToAdmins(alarm);
        alarms.forEach(webSocketController::sendNotificationToMember);
    }
}
