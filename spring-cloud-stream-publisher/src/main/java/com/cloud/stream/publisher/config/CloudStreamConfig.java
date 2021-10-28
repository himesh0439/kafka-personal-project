package com.cloud.stream.publisher.config;

import com.cloud.stream.publisher.model.Data;
import com.cloud.stream.publisher.model.User;
import com.cloud.stream.publisher.service.DataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class CloudStreamConfig {

    private final DataService service;

    @Bean
    public Consumer<List<Data>> consumeData() {
        return data -> {
            log.info("Received Data [{}]", data);
            var user = new User(data);
            service.sendUsers(user);
        };
    }
}
