package com.fuzz.movies.controller;

import com.fuzz.movies.model.Movie;
import com.fuzz.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/save")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable long id) {
        return ResponseEntity.of(movieService.getMovieById(id));
    }

    @GetMapping("/movies")
    public List<Movie> getMoviesBySearch(@RequestParam("search") String search) {
        return movieService.findMoviesBySearch(search);
    }

    @PostMapping("/favorite/{id}")
    public ResponseEntity<Movie> addAsFavorite(@PathVariable long id) {
        return ResponseEntity.of(movieService.addFavorite(id));
    }

    @GetMapping("/favorites")
    public List<Movie> getFavorites() {
        return movieService.getFavoriteMovies();
    }

}
