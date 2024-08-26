package com.aryan.movie_ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aryan.movie_ticket.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	boolean existsByEmail(String email);

	boolean existsByMobile(long mobile);

	Customer findByMobile(long mobile);

	Customer findByEmail(String email);

}
