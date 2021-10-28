package com.example.restproject.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Data(String id, String email, @JsonProperty("first_name") String firstName,
                   @JsonProperty("last_name") String lastName, String avatar) {
}
