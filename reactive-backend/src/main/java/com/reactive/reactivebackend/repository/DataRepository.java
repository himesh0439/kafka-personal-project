package com.reactive.reactivebackend.repository;

import com.reactive.reactivebackend.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Data, String> {
}
