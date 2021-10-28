package com.cloud.stream.publisher.service;

import com.cloud.stream.publisher.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataService {

    private final WebClient client;

    public void sendUsers(User user) {

        client.post()
                .uri(uriBuilder -> uriBuilder.path("save/data").build())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, resp -> {
                    log.error("ClientError {}", resp.statusCode());
                    return Mono.error(new RuntimeException("ClientError"));
                })
                .bodyToMono(User.class)
                .doOnSuccess(users -> log.info("Retrieved Users Successfully {}", users))
                .subscribe();

    }
}
