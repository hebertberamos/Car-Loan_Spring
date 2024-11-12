package com.personalproject.carloan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CarloanApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarloanApplication.class, args);
	}

}
