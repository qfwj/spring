package com.qf.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkatestApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkatestApplication.class, args);
	}
}
