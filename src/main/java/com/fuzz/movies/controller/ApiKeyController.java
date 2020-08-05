package com.fuzz.movies.controller;

import com.fuzz.movies.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiKeyController {

    private ApiKeyService apiKeyService;

    @Autowired
    public ApiKeyController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @PostMapping("/apikey")
    public Map<String, String> getTemporalApiKey() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        HashMap<String, String> apiKey = new HashMap<>();
        String newApiKey = apiKeyService.createApiKey();
        apiKeyService.addApiKey(newApiKey);
        apiKey.put("apiKey", newApiKey);
        return apiKey;
    }

    @Scheduled(cron = "0 * * * * *")
    public void deleteKeys() {
        apiKeyService.deleteAllApiKeys();
    }

}
