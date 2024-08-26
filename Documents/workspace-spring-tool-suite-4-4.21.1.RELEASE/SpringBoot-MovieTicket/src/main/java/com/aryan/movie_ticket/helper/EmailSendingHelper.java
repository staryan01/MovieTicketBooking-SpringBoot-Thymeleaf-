package com.aryan.movie_ticket.helper;

import org.apache.http.entity.mime.content.StringBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import com.aryan.movie_ticket.dto.Customer;
import com.aryan.movie_ticket.dto.Theatre;

import jakarta.mail.internet.MimeMessage;
import jakarta.validation.MessageInterpolator.Context;
import jakarta.validation.Valid;

@Component
public class EmailSendingHelper 
{
		@Autowired
		JavaMailSender mailSender;
		
		@Autowired
		TemplateEngine templateEngine;
		
		public void sendMailToCustomer(Customer customer)
		{
			MimeMessage message=mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(message);
			
			try
			{
				helper.setFrom("aryankumar0282000@gmail.com", "Movie-Ticket-Site");
				helper.setTo(customer.getEmail());
				helper.setSubject("Email Verification OTP");
				org.thymeleaf.context.Context context= new org.thymeleaf.context.Context();
				context.setVariable("customer", customer);
				String body= templateEngine.process("my-email-template", context);
				helper.setText(body,true);
				mailSender.send(message);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void sendMailToTheatre(@Valid Theatre theatre) 
		{
			MimeMessage message=mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(message);
			
			try
			{
				helper.setFrom("aryankumar0282000@gmail.com", "Movie-Ticket-Site");
				helper.setTo(theatre.getEmail());
				helper.setSubject("Email Verification OTP");
				org.thymeleaf.context.Context context= new org.thymeleaf.context.Context();
				context.setVariable("customer", theatre);
				String body= templateEngine.process("my-email-template", context);
				helper.setText(body,true);
				mailSender.send(message);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
}
