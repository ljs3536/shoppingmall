package com.hertz.shoppingMall.utils.log.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(indexName = "access-logs")
public class AccessLog {
    @Id
    private String id;

    @Field(type = FieldType.Date)
    private LocalDateTime timestamp;

    @Field(type = FieldType.Keyword)
    private String path;

    @Field(type = FieldType.Keyword)
    private String method;

    @Field(type = FieldType.Long)
    private long executionTime;

    @Field(type = FieldType.Keyword)
    private String userId;

    @Field(type = FieldType.Text)
    private String userAgent;

    @Field(type = FieldType.Keyword)
    private String ipAddress;

}