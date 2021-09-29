package com.umeet.umeet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UmeetApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmeetApplication.class, args);
	}

}
