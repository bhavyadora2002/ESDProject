package com.bhavya.esdbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class EsdbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsdbackendApplication.class, args);
	}

}
