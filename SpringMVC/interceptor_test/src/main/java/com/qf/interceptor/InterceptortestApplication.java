package com.qf.interceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableAutoConfiguration
public class InterceptortestApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterceptortestApplication.class, args);
		CorsFilter sds ;
	}
}
