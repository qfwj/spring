package com.example.qfcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CloudtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudtestApplication.class, args);
	}
}
