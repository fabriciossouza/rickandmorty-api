package com.github.fabriciossouza.rickandmortyapi;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import static org.springframework.boot.SpringApplication.run;


@EnableFeignClients(basePackages = "com.github.fabriciossouza.rickandmortyapi.domain.client")
@SpringBootApplication
public class RickandmortyapiApplication {

	public static void main(String[] args) {
		run(RickandmortyapiApplication.class, args);
	}

}
