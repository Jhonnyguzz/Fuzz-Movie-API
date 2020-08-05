package com.fuzz.movies.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class ApiKey {

    @Id
    private String apiKey;

    public ApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

}
