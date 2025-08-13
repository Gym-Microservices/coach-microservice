package com.gym.coach_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CoachMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoachMicroserviceApplication.class, args);
	}

}
