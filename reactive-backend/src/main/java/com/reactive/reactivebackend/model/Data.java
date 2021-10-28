package com.reactive.reactivebackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@lombok.Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Data {
    @Id
    @NonNull
    private String id;

    private String email;

    @Column(name = "first_name")
    @JsonProperty("first_name")
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty("last_name")
    private String lastName;
    private String avatar;
}
