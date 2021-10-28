package com.reactive.reactivebackend.controller;

import com.reactive.reactivebackend.model.User;
import com.reactive.reactivebackend.service.DataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/save")
@RequiredArgsConstructor
@Slf4j
public class DataController {

    private final DataService dataService;

    @PostMapping("/data")
    public ResponseEntity<User> getAllUsers(@RequestBody User user) {

        log.info("Saving data in db [{}]", user);
        dataService.saveData(user.dataList());
        return ResponseEntity.ok(user);
    }
}
