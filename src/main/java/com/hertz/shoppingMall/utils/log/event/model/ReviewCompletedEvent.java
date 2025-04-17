package com.hertz.shoppingMall.utils.log.event.model;

import com.hertz.shoppingMall.review.model.Review;
import lombok.Getter;

@Getter
public class ReviewCompletedEvent {

    private final Review review;

    public ReviewCompletedEvent(Review review){
        this.review = review;
    }

}
