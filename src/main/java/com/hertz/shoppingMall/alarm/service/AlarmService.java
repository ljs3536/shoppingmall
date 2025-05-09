package com.hertz.shoppingMall.alarm.service;

import com.hertz.shoppingMall.alarm.dto.AlarmResponseDto;
import com.hertz.shoppingMall.alarm.model.Alarm;
import com.hertz.shoppingMall.alarm.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;


    public List<AlarmResponseDto> getUnreadAlarms(Long memberId) {
        return alarmRepository.findByMemberIdAndIsReadFalseOrderByCreatedAtDesc(memberId);
    }

    @Transactional
    public void markAsRead(Long alarmId, Long memberId) {
        Alarm alarm = alarmRepository.findByIdAndTargetMemberId(alarmId, memberId)
                .orElseThrow(() -> new IllegalArgumentException("알림을 찾을 수 없습니다."));
        alarm.setRead(true);
    }

    @Transactional
    public void deleteAlarm(Long alarmId, Long memberId) {
        Alarm alarm = alarmRepository.findByIdAndTargetMemberId(alarmId, memberId)
                .orElseThrow(() -> new IllegalArgumentException("알림을 찾을 수 없습니다."));
        alarmRepository.delete(alarm);
    }
}
