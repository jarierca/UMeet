package com.umeet.umeet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UmeetFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmeetFrontApplication.class, args);
	}

}
