package com.hertz.shoppingMall.review.service;

import com.hertz.shoppingMall.order.model.OrderItem;
import com.hertz.shoppingMall.order.model.OrderStatus;
import com.hertz.shoppingMall.order.repository.OrderItemRepository;
import com.hertz.shoppingMall.review.dto.ReviewForm;
import com.hertz.shoppingMall.review.model.Review;
import com.hertz.shoppingMall.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public void createReview(ReviewForm form) {
        OrderItem orderItem = orderItemRepository.findById(form.getOrderItemId())
                .orElseThrow(() -> new IllegalArgumentException("주문 상품을 찾을 수 없습니다."));

        Review review = new Review();
        review.setOrderItem(orderItem);
        review.setProduct(orderItem.getProduct());
        review.setMember(orderItem.getOrder().getMember());
        review.setRating(form.getRating());
        review.setContent(form.getContent());

        reviewRepository.save(review);

        orderItem.setStatus(OrderStatus.REVIEWED);
    }

    public Page<Review> getReviewsByProduct(Long productId, Pageable pageable){
        return reviewRepository.findByProductId(productId, pageable);
    }
}
