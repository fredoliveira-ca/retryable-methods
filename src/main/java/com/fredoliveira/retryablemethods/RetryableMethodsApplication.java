package com.fredoliveira.retryablemethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class RetryableMethodsApplication implements CommandLineRunner {

	private StuffService stuffService;

	@Autowired
	public RetryableMethodsApplication(StuffService stuffService) {
		this.stuffService = stuffService;
	}

	public static void main(String[] args) {
		SpringApplication.run(RetryableMethodsApplication.class, args);
	}

	@Override
	public void run(String... args) {
		try {
			stuffService.doSomeStuff();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
