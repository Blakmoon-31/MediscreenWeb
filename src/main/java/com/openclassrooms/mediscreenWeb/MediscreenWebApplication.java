package com.openclassrooms.mediscreenWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.openclassrooms.mediscreenWeb")
public class MediscreenWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenWebApplication.class, args);
	}

}
