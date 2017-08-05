package com.qf.dubbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DubboxclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboxclientApplication.class, args);
		try {
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
