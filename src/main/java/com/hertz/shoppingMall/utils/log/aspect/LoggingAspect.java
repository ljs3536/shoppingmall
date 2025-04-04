package com.hertz.shoppingMall.utils.log.aspect;

import com.hertz.shoppingMall.config.security.CustomUserDetails;
import com.hertz.shoppingMall.utils.log.model.AccessLog;
import com.hertz.shoppingMall.utils.log.repository.AccessLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/*
* */
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final AccessLogRepository accessLogRepository;

    @Pointcut("execution(* com.hertz.shoppingMall.*.controller.*.*(..))")
    public void controllerMethods() {}

    @Around("controllerMethods()")
    public Object logControllerAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // 컨트롤러 메서드 실행
        Object result = joinPoint.proceed();

        // 실행 후 로그 기록
        long executionTime = System.currentTimeMillis() - startTime;

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        AccessLog accessLog = new AccessLog();
        accessLog.setTimestamp(LocalDateTime.now());
        accessLog.setPath(request.getRequestURI());
        accessLog.setMethod(request.getMethod());
        accessLog.setExecutionTime(executionTime);

        // CustomUserDetails에서 memberId를 추출
        String userId = getCurrentUserId();
        accessLog.setUserId(userId);

        accessLog.setUserAgent(request.getHeader("User-Agent"));
        accessLog.setIpAddress(request.getRemoteAddr());

        // Elasticsearch에 저장
        accessLogRepository.save(accessLog);

        return result;
    }

    private String getCurrentUserId() {
        // SecurityContextHolder에서 Authentication 객체 추출
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 인증된 사용자인 경우 CustomUserDetails에서 memberId 추출
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails.getUsername(); // Long을 String으로 변환
        }

        // 인증되지 않은 사용자 또는 CustomUserDetails가 아닌 경우
        return "anonymous";
    }
}
