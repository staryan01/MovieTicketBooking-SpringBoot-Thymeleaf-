package com.aryan.movie_ticket.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aryan.movie_ticket.dto.Customer;
import com.aryan.movie_ticket.dto.Theatre;
import com.aryan.movie_ticket.helper.AES;
import com.aryan.movie_ticket.helper.EmailSendingHelper;
import com.aryan.movie_ticket.repository.CustomerRepository;
import com.aryan.movie_ticket.repository.TheatreRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController 
{
	@Autowired
	Customer customer;
	
	@Autowired
	Theatre theatre;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	TheatreRepository theatreRepository;
	
	@Autowired
	EmailSendingHelper emailSendingHelper;
	
	@GetMapping("/signup")
	public String loadSignup(ModelMap map)
	{
		map.put("customer", customer);
		return "customer-signup.html";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid Customer customer , BindingResult result,HttpSession session)
	{
		if(!customer.getPassword().equals(customer.getConfirmPassword()))
		{
			//below line accept field , error code and Default message
			result.rejectValue("confirmPassword", "error.confirmPassword" , "* Password Mismatch");
		}
		if(customerRepository.existsByEmail(customer.getEmail()) || theatreRepository.existsByEmail(customer.getEmail()))
		{
			result.rejectValue("email", "error.email", "* Account already Exist");
		}
		if(customerRepository.existsByMobile(customer.getMobile()) || theatreRepository.existsByMobile(customer.getMobile()))
		{
			result.rejectValue("mobile", "error.mobile", "* Account already Exist");
		}
		
		if(result.hasErrors())
		{
			return "customer-signup.html";
		}
		else
		{
			customer.setPassword(AES.encrypt(customer.getPassword(), "123"));
			customer.setOtp(new Random().nextInt(100000,1000000));
			System.out.println("Otp -> "+customer.getOtp());
			emailSendingHelper.sendMailToCustomer(customer);
			customerRepository.save(customer);
			session.setAttribute("success", "otp sent successfull");
			session.setAttribute("id", customer.getId());
			return "redirect:/customer/enter-otp";
		}	
	}
	
	@GetMapping("/enter-otp")
	public String enterOtp(ModelMap map)
	{
		map.put("user", "customer");
		return "enter-otp.html";
	}
	
	@PostMapping("/verify-otp")
	public String verifyotp(@RequestParam int id ,@RequestParam int otp , HttpSession session)
	{
		Customer customer = customerRepository.findById(id).orElseThrow();
		if(customer.getOtp()==otp)
		{
			customer.setVerified(true);
			customerRepository.save(customer);
			session.setAttribute("success", "Account Created Success");
			return "redirect:/login";
		}
		else
		{
			session.setAttribute("failure", "Invalid OTP! Try again");
			return "redirect:/customer/enter-otp";
		}
	}
}
