package com.olx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class OlxEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxEurekaApplication.class, args);
	}

}
