package com.umeet.umeet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class UmeetBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmeetBackApplication.class, args);
	}

}
