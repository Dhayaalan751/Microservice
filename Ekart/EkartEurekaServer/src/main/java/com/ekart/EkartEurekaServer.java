package com.ekart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EkartEurekaServer {

	public static void main(String[] args) {
		SpringApplication.run(EkartEurekaServer.class, args);
	}

}
