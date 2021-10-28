package com.cloud.stream.publisher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private static final String BASE_URL = "http://localhost:8080/";

    @Bean
    public WebClient buildWebClient() {
        return WebClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }
}
