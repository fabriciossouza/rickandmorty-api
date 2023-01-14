package com.github.fabriciossouza.rickandmortyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "com.github.fabriciossouza.rickandmortyapi.domain.client")
@SpringBootApplication
public class RickandmortyapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RickandmortyapiApplication.class, args);
	}

}
