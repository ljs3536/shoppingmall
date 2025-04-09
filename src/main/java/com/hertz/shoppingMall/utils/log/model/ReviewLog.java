package com.hertz.shoppingMall.utils.log.model;

import com.hertz.shoppingMall.review.model.Review;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(indexName = "review_products-logs")
public class ReviewLog {
    @Id
    private String id;

    @Field(type = FieldType.Date)
    private LocalDateTime timestamp;

    // 유저 정보
    @Field(type = FieldType.Keyword)
    private String username;
    @Field(type = FieldType.Keyword)
    private Integer userAge;
    @Field(type = FieldType.Keyword)
    private String userRegion;
    @Field(type = FieldType.Keyword)
    private String userGender;

    // 상품 정보
    @Field(type = FieldType.Keyword)
    private String productName;
    @Field(type = FieldType.Keyword)
    private Integer productPrice;
    @Field(type = FieldType.Keyword)
    private Integer productQuantity;
    @Field(type = FieldType.Text)
    private String productDescription;
    @Field(type = FieldType.Keyword)
    private String productCategory;

    // 리뷰 정보
    @Field(type = FieldType.Keyword)
    private Integer rating;
    @Field(type = FieldType.Keyword)
    private String description;


    public static ReviewLog createReviewLog(Review review) {
        ReviewLog log = new ReviewLog();
        log.setTimestamp(LocalDateTime.now());
        log.setTimestamp(LocalDateTime.now());
        log.setUsername(review.getMember().getUsername());
        log.setUserRegion(review.getMember().getRegion());
        log.setUserAge(review.getMember().getAge());
        log.setUserGender(review.getMember().getGender());
        log.setProductName(review.getProduct().getName());
        log.setProductPrice(review.getProduct().getPrice());
        log.setProductQuantity(review.getOrderItem().getQuantity());
        log.setProductCategory(review.getProduct().getCategory().getName());
        log.setRating(review.getRating());
        log.setDescription(review.getContent());
        return log;
    }
}
