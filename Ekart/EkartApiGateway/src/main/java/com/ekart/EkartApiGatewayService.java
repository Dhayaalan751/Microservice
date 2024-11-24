package com.ekart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class EkartApiGatewayService {

	public static void main(String[] args) {
		SpringApplication.run(EkartApiGatewayService.class, args);
	}

}
