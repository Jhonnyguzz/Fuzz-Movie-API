package com.fuzz.movies.service.impl;

import com.fuzz.movies.model.ApiKey;
import com.fuzz.movies.repository.ApiKeyRepository;
import com.fuzz.movies.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private ApiKeyRepository apiKeyRepository;

    @Autowired
    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public boolean apiKeyExist(String apiKey) {
        return apiKeyRepository.findById(apiKey).isPresent();
    }

    @Override
    public void addApiKey(String apiKey) {
        apiKeyRepository.save(new ApiKey(apiKey));
    }

    @Override
    public String createApiKey() throws NoSuchAlgorithmException {
        MessageDigest salt = MessageDigest.getInstance("SHA-256");
        byte[] hash = salt.digest(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    @Override
    public void deleteAllApiKeys() {
        apiKeyRepository.deleteAll();
    }

}
