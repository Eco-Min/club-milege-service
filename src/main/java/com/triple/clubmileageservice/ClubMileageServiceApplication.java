package com.triple.clubmileageservice;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;

@SpringBootApplication
@EnableJpaAuditing
public class ClubMileageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClubMileageServiceApplication.class, args);
	}
	@Bean
	JPAQueryFactory jpaQueryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}

}
