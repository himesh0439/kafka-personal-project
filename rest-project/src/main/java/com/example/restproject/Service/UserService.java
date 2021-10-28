package com.example.restproject.Service;

import com.example.restproject.Model.CreateUser;
import com.example.restproject.Model.CreatedUser;
import com.example.restproject.Model.Data;
import com.example.restproject.Model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private static final String TOPIC = "user-data";

    private final WebClient webClient;

    private final KafkaTemplate<String, List<Data>> kafkaTemplate;

    public Mono<User> getUsers(String pageNumber) {

        var client = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/users").queryParam("page", pageNumber).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, resp -> {
                    log.error("ClientError {}", resp.statusCode());
                    return Mono.error(new RuntimeException("ClientError"));
                })
                .bodyToMono(User.class)
                .doOnSuccess(user -> log.info("Retrieved Users Successfully {}", user));

        client.subscribe(user ->  kafkaTemplate.send(TOPIC, user.data()));

        return client;
    }

    private Mono<CreatedUser> createUser(Mono<CreateUser> createUser) {
        return webClient.post()
                .uri("api/users")
                .body(createUser, CreateUser.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, resp -> {
                    log.error("ClientError {}", resp.statusCode());
                    return Mono.error(new RuntimeException("ClientError"));
                })
                .bodyToMono(CreatedUser.class)
                .doOnSuccess(user -> log.info("Created User Successfully {}", user));
    }

    public Mono<ServerResponse> createUserHandler(ServerRequest serverRequest) {
        var createUser = serverRequest.bodyToMono(CreateUser.class);
        return ok().body(createUser(createUser), CreatedUser.class);
    }

    public Mono<ServerResponse> getUserHandler(ServerRequest serverRequest) {
        var pageNumber = serverRequest.queryParam("page").orElse("2");
        return ok().body(getUsers(pageNumber), CreatedUser.class);
    }

}
