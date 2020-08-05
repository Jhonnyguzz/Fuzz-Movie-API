package com.fuzz.movies.controller;

import com.fuzz.movies.exception.ApiKeyNotFoundException;
import com.fuzz.movies.model.Movie;
import com.fuzz.movies.service.ApiKeyService;
import com.fuzz.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class MovieController {

    private MovieService movieService;
    private ApiKeyService apiKeyService;

    @Autowired
    public MovieController(MovieService movieService, ApiKeyService apiKeyService) {
        this.movieService = movieService;
        this.apiKeyService = apiKeyService;
    }

    @PostMapping("/save")
    public Movie addMovie(@RequestBody Movie movie, @RequestParam("apiKey") String apiKey) {
        validateApiKey(apiKey);
        return movieService.saveMovie(movie);
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable long id, @RequestParam("apiKey") String apiKey) {
        validateApiKey(apiKey);
        return ResponseEntity.of(movieService.getMovieById(id));
    }

    @GetMapping("/movies")
    public List<Movie> getMoviesBySearch(@RequestParam("search") String search, @RequestParam("apiKey") String apiKey) {
        validateApiKey(apiKey);
        return movieService.findMoviesBySearch(search);
    }

    @PostMapping("/favorite/{id}")
    public ResponseEntity<Movie> addAsFavorite(@PathVariable long id, @RequestParam("apiKey") String apiKey) {
        validateApiKey(apiKey);
        return ResponseEntity.of(movieService.addFavorite(id));
    }

    @GetMapping("/favorites")
    public List<Movie> getFavorites(@RequestParam("apiKey") String apiKey) {
        validateApiKey(apiKey);
        return movieService.getFavoriteMovies();
    }

    private void validateApiKey(String apiKey) {
        boolean isValidApiKey = apiKeyService.apiKeyExist(apiKey);
        if(!isValidApiKey)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Api Key was not found, try requesting a new one", new ApiKeyNotFoundException());
    }

}
