package com.loan.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LoanDomainServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanDomainServiceApplication.class, args);
	}

}
