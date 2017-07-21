package com.example.qfcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer

public class CloudconfigurationcenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudconfigurationcenterApplication.class, args);
	}
}
