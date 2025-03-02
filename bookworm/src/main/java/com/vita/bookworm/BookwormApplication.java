package com.vita.bookworm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
@EntityScan(basePackages = "com.vita.models")
@ComponentScan(basePackages = "com.vita.bookworm")
public class BookwormApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookwormApplication.class, args);
	}

}
