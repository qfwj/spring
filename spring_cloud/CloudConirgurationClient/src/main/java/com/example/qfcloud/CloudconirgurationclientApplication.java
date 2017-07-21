package com.example.qfcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
@RefreshScope
public class CloudconirgurationclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudconirgurationclientApplication.class, args);
	}

	@Value("${from}")
	String from;
	@RequestMapping(value = "/hi")
	public String hi(){
		return from;
	}
}
