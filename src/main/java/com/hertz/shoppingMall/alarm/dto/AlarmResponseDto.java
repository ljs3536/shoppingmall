package com.hertz.shoppingMall.alarm.dto;

import com.hertz.shoppingMall.alarm.model.Alarm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AlarmResponseDto {
    private Long id;
    private String title;
    private String content;
    private String time; // ex. "3분 전", 또는 LocalDateTime 포맷된 문자열
}

