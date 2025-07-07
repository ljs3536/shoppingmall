package com.hertz.shoppingMall.config.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession // Redis 기반 HttpSession 사용하도록 설정
public class SessionConfig {
}
