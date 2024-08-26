package com.aryan.movie_ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aryan.movie_ticket.dto.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{

}
