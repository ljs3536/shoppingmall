package com.hertz.shoppingMall.alarm.repository;

import com.hertz.shoppingMall.alarm.model.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface AlarmRepository extends JpaRepository<Alarm, Long>, AlarmRepositoryCustom {
    Optional<Alarm> findByIdAndTargetMemberId(Long id, Long memberId);
}