package com.example.coreCRM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoreCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreCrmApplication.class, args);
	}

}
