package com.qf.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationContextInitializer;

@SpringBootApplication

public class BanertestApplication {

	public static void main(String[] args) {
		LoggingApplicationListener

		SpringApplication.run(BanertestApplication.class, args);
	}
}
