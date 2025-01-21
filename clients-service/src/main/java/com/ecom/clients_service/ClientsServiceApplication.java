package com.ecom.clients_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ecom.clients_service")
public class ClientsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientsServiceApplication.class, args);
	}

}
