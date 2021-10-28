package com.reactive.reactivebackend.service;

import com.reactive.reactivebackend.model.Data;
import com.reactive.reactivebackend.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataService {

    private final DataRepository dataRepository;

    public void saveData(List<Data> dataList) {
        log.info("Saving Data in schema [{}]", dataList);
        dataRepository.saveAll(dataList);
    }
}
