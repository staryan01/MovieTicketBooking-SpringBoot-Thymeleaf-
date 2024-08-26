package com.aryan.movie_ticket.dto;

import java.time.LocalDate;

import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Component
public class Customer 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id;
	
	@Size(min=3 , max=20 , message = "* Enter between 3-20 characters")
	private String name;
	
	@DecimalMin(value = "6000000000" , message = "* Enter proper mobile no")
	@DecimalMax(value = "9999999999" , message = "* Enter proper mobile no")
	private long mobile;
	
	@NotEmpty(message = "* This is compulsory feild")
	@Email(message = "* Enter proper mail")
	private String email;
	
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$" , message = "Enter 8 character with one lowercase,one uppercase,one number and one specail character")
	private String password;
	
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$" , message = "Enter 8 character with one lowercase,one uppercase,one number and one specail character")
	@Transient
	private String confirmPassword;

	@NotNull(message = "*it is compulsory feild ")
	private String gender;
	
	@Past(message = "*Enter proper DOB")
	@NotNull(message = "*it is compulsory feild ")
	private LocalDate dob;
	
	private int otp;
	private boolean verified;
}
