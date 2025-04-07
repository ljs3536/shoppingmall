package com.hertz.shoppingMall.config.webclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .baseUrl("http://localhost:5000") // Flask 서버 주소
                .build();
    }
}
