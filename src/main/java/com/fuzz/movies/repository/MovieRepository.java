package com.fuzz.movies.repository;

import com.fuzz.movies.model.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

}
