package com.aryan.movie_ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aryan.movie_ticket.dto.Theatre;

public interface TheatreRepository extends JpaRepository<Theatre, Integer>
{



	boolean existsByEmail(String email);

	boolean existsByMobile(long mobile);

	Theatre findByMobile(long mobile);

	Theatre findByEmail(String email);

	List<Theatre> findByApprovedFalseAndVerifiedTrue();

}
