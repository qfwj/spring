package com.qfcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class CloudzipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudzipkinApplication.class, args);
	}
}
