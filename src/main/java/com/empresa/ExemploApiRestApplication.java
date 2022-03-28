package com.empresa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class ExemploApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExemploApiRestApplication.class, args);
	}

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Minha Empresa API").description("Exemplo de documentação").version("v0.0.1"));
	}

}
