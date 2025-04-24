package com.hertz.shoppingMall.config.webclient;

import io.netty.channel.ChannelOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Value("${flask.base-url}")
    private String flaskBaseUrl;

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .baseUrl(flaskBaseUrl)
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create()
                                .responseTimeout(Duration.ofSeconds(8))
                                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 8000)
                ))
                .build();
    }
}
