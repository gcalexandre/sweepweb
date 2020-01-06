package com.alexandrecasrtilho.websweep.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.alexandrecasrtilho.websweep.services.EmailService;
import com.alexandrecasrtilho.websweep.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class ProdConfig {
	
	
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}

