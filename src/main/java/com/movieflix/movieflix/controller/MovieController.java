package com.movieflix.movieflix.controller;

import com.movieflix.movieflix.entity.Movie;
import com.movieflix.movieflix.mapper.MovieMapper;
import com.movieflix.movieflix.request.MovieRequest;
import com.movieflix.movieflix.response.MovieResponse;
import com.movieflix.movieflix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse> saveMovie(@RequestBody MovieRequest movieRequest) {
        Movie newMovie = movieService.save(MovieMapper.toMovie(movieRequest));
        MovieResponse movie = MovieMapper.toMovieResponse(newMovie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        List<Movie> movies = movieService.findAll();
        List<MovieResponse> movieResponses = movies.stream()
                .map(movie -> MovieMapper.toMovieResponse(movie))
                //.map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok(movieResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieService.findById(id);
        if (movie.isPresent()) {
            MovieResponse movieResponse = MovieMapper.toMovieResponse(movie.get());
            return ResponseEntity.ok(movieResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieService.findById(id);
        if (movie.isPresent()) {
            movieService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
