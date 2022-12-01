package com.marketingapp.utility;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component  //which is nothing but this class maintense  between spring boot ,if not using component we can not using @autowired ,it calss mainten by spring otherwise not work
public class EmailServiceImpl implements EmailService {

	 @Autowired
	    private JavaMailSender emailSender;

	    public void sendSimpleMessage(
	      String to, String subject, String text) {
	        
	        SimpleMailMessage message = new SimpleMailMessage(); 
	     
	        message.setTo(to); 
	        message.setSubject(subject); 
	        message.setText(text);
	        emailSender.send(message);
	        
	    }
		
	}


