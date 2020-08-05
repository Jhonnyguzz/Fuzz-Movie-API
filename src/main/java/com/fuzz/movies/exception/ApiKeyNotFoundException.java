package com.fuzz.movies.exception;

import org.springframework.stereotype.Component;

@Component
public class ApiKeyNotFoundException extends RuntimeException {

    public ApiKeyNotFoundException() {
        super("Api Key was not found, try requesting a new one");
    }

}
