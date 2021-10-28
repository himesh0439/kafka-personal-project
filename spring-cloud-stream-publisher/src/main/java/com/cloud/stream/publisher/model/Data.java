package com.cloud.stream.publisher.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Data {
    private String id;

    private String email;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;
    private String avatar;
}
