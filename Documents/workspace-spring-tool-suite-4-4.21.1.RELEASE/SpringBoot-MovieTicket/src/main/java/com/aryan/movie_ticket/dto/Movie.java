package com.aryan.movie_ticket.dto;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Movie 
{
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int id;
	private String name;
	private String genere;
	private String description;
	private int duration;
	private String cast;
	private String movie_poster;
	private String trailerLink;
	private LocalDate releaseDate;
}
