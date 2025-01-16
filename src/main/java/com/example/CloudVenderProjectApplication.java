package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudVenderProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudVenderProjectApplication.class, args);
		System.out.println("this project has done unit testing cicd and docker file");
	}

}
