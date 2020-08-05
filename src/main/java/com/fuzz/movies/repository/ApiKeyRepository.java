package com.fuzz.movies.repository;

import com.fuzz.movies.model.ApiKey;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApiKeyRepository extends PagingAndSortingRepository<ApiKey, String> {

}
