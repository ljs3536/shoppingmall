package com.hertz.shoppingMall.alarm.repository;

import com.hertz.shoppingMall.alarm.dto.AlarmResponseDto;
import com.hertz.shoppingMall.alarm.model.QAlarm;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AlarmRepositoryImpl implements AlarmRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<AlarmResponseDto> findByMemberIdAndIsReadFalseOrderByCreatedAtDesc(Long memberId) {
        QAlarm alarm = QAlarm.alarm;

        return queryFactory
                .select(Projections.constructor(
                        AlarmResponseDto.class,
                        alarm.id,
                        alarm.title,
                        alarm.content,
                        alarm.createdAt.stringValue() // 또는 alarm.createdAt 그대로 전달
                ))
                .from(alarm)
                .where(
                        alarm.targetMember.id.eq(memberId),
                        alarm.isRead.eq(false)
                )
                .orderBy(alarm.createdAt.desc())
                .fetch();
    }
}
