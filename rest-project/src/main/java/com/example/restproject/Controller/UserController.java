package com.example.restproject.Controller;

import com.example.restproject.Model.Data;
import com.example.restproject.Model.User;
import com.example.restproject.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{pageNumber}")
    public ResponseEntity<User> getAllUsers(@PathVariable String pageNumber) {

        User user = userService.getUsers(pageNumber).block();

        var images = Optional.ofNullable(user).stream().map(User::data).flatMap(Collection::stream)
                .map(Data::avatar).collect(Collectors.toList());

        return ResponseEntity.ok(user);
    }
}
