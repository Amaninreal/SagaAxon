package com.progressivecoder.ordermanagement.orderservice.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Saga Pattern Implementation using Axon and Spring Boot")
                        .version("1.0.0")
                        .description("App to demonstrate Saga Pattern using Axon and Spring Boot")
                        .contact(new Contact()
                                .name("Aman Jha")
                                .email("coder.progressive@gmail.com")
                                .url("https://progressivecoder.com")
                        )
                );
    }
}