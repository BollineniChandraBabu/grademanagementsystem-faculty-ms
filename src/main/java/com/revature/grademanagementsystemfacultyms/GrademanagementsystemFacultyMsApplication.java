package com.revature.grademanagementsystemfacultyms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class GrademanagementsystemFacultyMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrademanagementsystemFacultyMsApplication.class, args);
	}
@Bean
public RestTemplate restTemplate () {
	return new RestTemplate ();
}
}
