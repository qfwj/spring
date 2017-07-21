package com.qfcloud.registerpeer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CloudeurekapeersecondApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudeurekapeersecondApplication.class, args);
	}
}
