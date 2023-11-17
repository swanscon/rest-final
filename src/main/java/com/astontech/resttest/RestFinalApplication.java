package com.astontech.resttest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

@SpringBootApplication
public class RestFinalApplication {

	final static Logger log = LoggerFactory.getLogger(RestFinalApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(RestFinalApplication.class, args);
		log.info("-------Application Started-------");
	}

}
