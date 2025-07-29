package com.pulgapps.movies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulgapps.movies.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    

}
