package com.hertz.shoppingMall.utils.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 비즈니스 로직 관련 에러
    DUPLICATE_MEMBER("M001", "이미 존재하는 회원입니다."),
    NOT_MATCH_ACCOUNT("M002", "아이디 또는 비밀번호가 일치하지 않습니다."),
    ACCOUNT_DISABLED("M003","계정이 비활성화되었습니다."),

    // 접근 권한 관련 에러
    ACCESS_DENIED("A001", "접근 권한이 없습니다."),

    // 인증 관련 에러
    UNAUTHORIZED("A002", "등록되지 않은 사용자입니다."),

    // 유효성 검증 관련 에러
    VALIDATION_ERROR("V001", "입력 값 검증에 실패했습니다."),

    // 내부 서버 에러
    INTERNAL_SERVER_ERROR("E001", "서버 내부 오류가 발생했습니다."),

    // IllegalStateException
    CONFLICT("C001", "리소스 충돌");

    private final String code;
    private final String message;
}
