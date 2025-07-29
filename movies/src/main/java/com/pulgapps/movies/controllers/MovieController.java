package com.pulgapps.movies.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulgapps.movies.models.Movie;
import com.pulgapps.movies.repositories.MovieRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @CrossOrigin
    @GetMapping    
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Optional <Movie> movie = movieRepository.findById(id);
        return movie.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping()
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie saveMovie = movieRepository.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveMovie);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        if (!movieRepository.existsById(id) ) {
            return ResponseEntity.notFound().build();
        }
        movieRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie updateMovie) {
        if (!movieRepository.existsById(id) ) {
            return ResponseEntity.notFound().build();
        }
        updateMovie.setId(id);
        Movie saveMovie = movieRepository.save(updateMovie);
        return ResponseEntity.ok(saveMovie);
    }
    

}
