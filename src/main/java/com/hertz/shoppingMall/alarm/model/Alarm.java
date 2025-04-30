package com.hertz.shoppingMall.alarm.model;

import com.hertz.shoppingMall.config.jpa.BaseDateEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Alarm extends BaseDateEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    private String title;
    private String content;
    private LocalDateTime createdAt;

    // 알림 대상
    @Enumerated(EnumType.STRING)
    private AlarmTarget target; // ADMIN, BUYER, SELLER, ALL, PERSONAL

    private Long targetMemberId; // 개인 대상일 경우에만 사용

    private boolean isRead = false; // 읽음 여부 (옵션)
}
