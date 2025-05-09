package com.hertz.shoppingMall.alarm.controller;

import com.hertz.shoppingMall.alarm.dto.AlarmResponseDto;
import com.hertz.shoppingMall.alarm.service.AlarmService;
import com.hertz.shoppingMall.config.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alarms")
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    @GetMapping
    public ResponseEntity<List<AlarmResponseDto>> getUnreadAlarms(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long memberId = userDetails.getMemberId();
        List<AlarmResponseDto> alarms = alarmService.getUnreadAlarms(memberId);
        return ResponseEntity.ok(alarms);
    }

    @PostMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable("id") Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        alarmService.markAsRead(id, userDetails.getMemberId());
        return ResponseEntity.ok().build();
    }

    // 알림 삭제 처리
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlarm(@PathVariable("id") Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        alarmService.deleteAlarm(id, userDetails.getMemberId());
        return ResponseEntity.noContent().build();
    }
}
