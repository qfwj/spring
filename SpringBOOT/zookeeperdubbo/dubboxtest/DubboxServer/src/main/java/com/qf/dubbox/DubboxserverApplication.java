package com.qf.dubbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DubboxserverApplication {
	public static void main(String[] args) {
		SpringApplication.run(DubboxserverApplication.class, args);
		try {
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}