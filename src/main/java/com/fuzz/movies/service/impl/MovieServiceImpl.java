package com.fuzz.movies.service.impl;

import com.fuzz.movies.model.Movie;
import com.fuzz.movies.repository.MovieRepository;
import com.fuzz.movies.service.MovieService;
import com.fuzz.movies.util.RegEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Optional<Movie> getMovieById(long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Optional<Movie> addFavorite(long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        movie.ifPresent(m -> {m.setFavorite(true); movieRepository.save(m);});
        return movie;
    }

    @Override
    public List<Movie> getFavoriteMovies() {
        return StreamSupport.stream(movieRepository.findAll().spliterator(), false)
                .filter(Movie::isFavorite)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> findMoviesBySearch(String search) {
        String regex = RegEx.toRegExpWithLowerAndUpper(search);

        Supplier<Stream<Movie>> originalStream = () -> StreamSupport.stream(movieRepository.findAll().spliterator(), false);

        Stream<Movie> byTitle = originalStream.get().filter(m -> m.getTitle().matches(regex));
        Stream<Movie> byLanguage = originalStream.get().filter(m -> m.getLanguage().matches(regex));
        Stream<Movie> byYear = originalStream.get().filter(m -> m.getYear().matches(regex));
        Stream<Movie> byDirector = originalStream.get().filter(m -> m.getDirector().matches(regex));
        Stream<Movie> byActor = originalStream.get().filter(m -> m.getActors().stream().anyMatch(a -> a.matches(regex)));

        return Stream.concat(Stream.concat(Stream.concat(byTitle, byLanguage), Stream.concat(byYear, byDirector)), byActor)
                .distinct().collect(Collectors.toList());
    }
}
