package com.example.restproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private static final String BASE_URL = "https://reqres.in";

    @Bean
    public WebClient buildWebClient() {
        return WebClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }
}
