package com.hertz.shoppingMall.alarm.repository;

import com.hertz.shoppingMall.alarm.dto.AlarmResponseDto;

import java.util.List;

public interface AlarmRepositoryCustom {
    List<AlarmResponseDto> findByMemberIdAndIsReadFalseOrderByCreatedAtDesc(Long memberId);
}
