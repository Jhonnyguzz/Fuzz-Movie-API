package com.fuzz.movies.service;

import com.fuzz.movies.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Movie saveMovie(Movie movie);

    Optional<Movie> getMovieById(long id);

    Optional<Movie> addFavorite(long id);

    List<Movie> getFavoriteMovies();

    List<Movie> findMoviesBySearch(String search);
}
