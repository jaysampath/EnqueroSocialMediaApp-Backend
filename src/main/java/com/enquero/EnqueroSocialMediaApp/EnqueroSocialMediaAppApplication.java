package com.enquero.EnqueroSocialMediaApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class EnqueroSocialMediaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnqueroSocialMediaAppApplication.class, args);
	}

}
