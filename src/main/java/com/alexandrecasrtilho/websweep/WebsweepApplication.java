package com.alexandrecasrtilho.websweep;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan({"com.alexandrecasrtilho.fileupdown.services"
	           ,"com.alexandrecasrtilho.fileupdown.config"
	           ,"com.alexandrecasrtilho.fileupdown.security"
	           ,"com.alexandrecasrtilho.fileupdown.resources"
	           })
@EntityScan("com.alexandrecasrtilho.fileupdown.domain")
@EnableJpaRepositories("com.alexandrecasrtilho.fileupdown.repositories")
public class WebsweepApplication implements CommandLineRunner  {
	
	
	public static void main(String[] args) {
		SpringApplication.run(WebsweepApplication.class, args);
		
		
	
	}

	@Override
	public void run(String... args) throws Exception {

	}

}