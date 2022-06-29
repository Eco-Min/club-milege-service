package com.triple.clubmileageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ClubMileageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClubMileageServiceApplication.class, args);
	}

}
