package com.hertz.shoppingMall.alarm.model;

public enum AlarmTarget {
    ADMIN,         // 관리자 공지용
    BUYER,         // 모든 구매자 대상
    SELLER,        // 모든 판매자 대상
    ALL,           // 모든 사용자
    PERSONAL       // 특정 개인 대상 (targetMemberId와 함께 사용)
}