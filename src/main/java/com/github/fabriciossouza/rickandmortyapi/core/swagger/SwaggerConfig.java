package com.github.fabriciossouza.rickandmortyapi.core.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API de Rick and Morty")
                    .description("Listagem de personagens da série Ricky and Morty")
                    .version("v1.0.0"));
    }
}