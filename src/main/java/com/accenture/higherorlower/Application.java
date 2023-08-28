package com.accenture.higherorlower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableWebSecurity
// @ComponentScan("com.accenture.higherorlower")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}