package com.hertz.shoppingMall.utils.log.model;

import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.order.model.OrderItem;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(indexName = "order_products-logs")
public class OrderLog {
    @Id
    private String id;

    @Field(type = FieldType.Date)
    private LocalDateTime timestamp;

    @Field(type = FieldType.Keyword)
    private String orderType;

    // 유저 정보
    @Field(type = FieldType.Keyword)
    private String userId;
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
    @Field(type = FieldType.Keyword)
    private String sellerId;

    public static OrderLog createOrderLog(Member member, OrderItem item, String orderType){
        OrderLog log = new OrderLog();
        log.setTimestamp(LocalDateTime.now());
        log.setOrderType(orderType);
        log.setUserId(member.getLoginId());
        log.setUserRegion(member.getRegion());
        log.setUserAge(member.getAge());
        log.setUserGender(member.getGender());
        log.setProductName(item.getProduct().getName());
        log.setProductPrice(item.getProduct().getPrice());
        log.setProductDescription(item.getProduct().getDescription());
        log.setProductQuantity(item.getQuantity());
        log.setProductCategory(item.getProduct().getCategory().getName());
        log.setSellerId(item.getProduct().getCreatedBy().getLoginId());
        return log;
    }
}
