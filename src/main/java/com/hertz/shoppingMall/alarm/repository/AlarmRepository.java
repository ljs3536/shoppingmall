package com.hertz.shoppingMall.alarm.repository;

import com.hertz.shoppingMall.alarm.model.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
}
