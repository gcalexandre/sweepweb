package com.alexandrecasrtilho.websweep.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.alexandrecasrtilho.websweep.domain.Cliente;

@Service
public interface EmailService {


	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}