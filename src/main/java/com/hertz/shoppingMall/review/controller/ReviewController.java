package com.hertz.shoppingMall.review.controller;

import com.hertz.shoppingMall.review.dto.ReviewForm;
import com.hertz.shoppingMall.review.model.Review;
import com.hertz.shoppingMall.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/buyer/review")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<?> createReview(@RequestBody @Valid ReviewForm form, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("입력값이 유효하지 않습니다.");
        }
        try {
            reviewService.createReview(form);
            return ResponseEntity.ok().body("리뷰가 등록되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "수량 업데이트 실패"));
        }
    }
}
