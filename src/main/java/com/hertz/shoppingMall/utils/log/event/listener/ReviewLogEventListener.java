package com.hertz.shoppingMall.utils.log.event.listener;

import com.hertz.shoppingMall.review.model.Review;
import com.hertz.shoppingMall.utils.log.event.model.ReviewCompletedEvent;
import com.hertz.shoppingMall.utils.log.model.ReviewLog;
import com.hertz.shoppingMall.utils.log.repository.ReviewLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReviewLogEventListener {

    private final ReviewLogRepository reviewLogRepository;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleReviewCompleted(ReviewCompletedEvent event){

        Review review = event.getReview();

        ReviewLog reviewLog = ReviewLog.createReviewLog(review);

        try{
            reviewLogRepository.save(reviewLog);
        }catch (Exception e){

        }
    }

}
