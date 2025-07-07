package com.hertz.shoppingMall.config.redis;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisConfigChk {

    @Value("${spring.session.redis.host:default}")
    private String redisHost;

    @PostConstruct
    public void init() {
        System.out.println("### Redis Host 확인: " + redisHost);
    }
}
