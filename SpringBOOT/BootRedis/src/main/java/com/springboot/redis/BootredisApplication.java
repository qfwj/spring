package com.springboot.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import redis.clients.jedis.Jedis;

@SpringBootApplication
@EnableRedisHttpSession
public class BootredisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootredisApplication.class, args);
	}
}
