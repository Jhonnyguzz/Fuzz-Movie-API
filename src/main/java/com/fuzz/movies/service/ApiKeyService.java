package com.fuzz.movies.service;

import java.security.NoSuchAlgorithmException;

public interface ApiKeyService {

    boolean apiKeyExist(String apiKey);

    void addApiKey(String apiKey);

    String createApiKey() throws NoSuchAlgorithmException;

    void deleteAllApiKeys();
}
