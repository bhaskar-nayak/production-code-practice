package com.production;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
public class ProductionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductionApplication.class, args);
	}

}
