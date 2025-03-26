package com.hertz.shoppingMall.utils.exception.handler;

import com.hertz.shoppingMall.utils.exception.ErrorCode;
import com.hertz.shoppingMall.utils.exception.ErrorResponse;
import com.hertz.shoppingMall.utils.exception.custom.DuplicateMemberException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

//
//    // 접근 권한 없음 예외 처리
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e){
//        log.error("Access Denied : {}", e.getMessage());
//
//        ErrorResponse errorResponse = ErrorResponse.builder()
//                .timestamp(LocalDateTime.now())
//                .status(HttpStatus.FORBIDDEN.value())
//                .error("Forbidden")
//                .message(ErrorCode.ACCESS_DENIED.getMessage())
//                .code(ErrorCode.ACCESS_DENIED.getCode())
//                .build();
//        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
//    }
//
//    // 유효성 검증 실패 예외 처리
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e){
//        Map<String, String> errors = new HashMap<>();
//        e.getBindingResult().getAllErrors().forEach(error ->{
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//
//        ErrorResponse errorResponse = ErrorResponse.builder()
//                .timestamp(LocalDateTime.now())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .error("Validation Error")
//                .message(ErrorCode.VALIDATION_ERROR.getMessage())
//                .code(ErrorCode.VALIDATION_ERROR.getCode())
//                .errors(errors)
//                .build();
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    // IllegalStateException 추가
//    @ExceptionHandler(IllegalStateException.class)
//    public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException e){
//
//        log.error("Illegal State Error : {}", e.getMessage());
//
//        ErrorResponse errorResponse = ErrorResponse.builder()
//                .timestamp(LocalDateTime.now())
//                .status(HttpStatus.CONFLICT.value())
//                .error("IllegalStateError")
//                .message("등록 정보에 문제가 있습니다.")
//                .code(ErrorCode.CONFLICT.getCode())
//                .build();
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
//    }

    //
    @ExceptionHandler(DuplicateMemberException.class)
    public String  handleDuplicateMemberException(
            DuplicateMemberException e,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("DuplicateMember")
                .code(ErrorCode.DUPLICATE_MEMBER.getCode())
                .message(ErrorCode.DUPLICATE_MEMBER.getMessage())
                .build();

        // ErrorResponse를 Model에 추가
        redirectAttributes.addFlashAttribute("errorResponse", errorResponse);

        // 회원 생성 폼으로 다시 리턴
        return "redirect:/members/form";
    }
}
