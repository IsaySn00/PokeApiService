package com.digis01.PokeApiService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PokeApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokeApiServiceApplication.class, args);
	}

}
