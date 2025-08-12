package com.senai.CadastroEstacionamento.infrastructure;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI oficinaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Cadastro estacionamento")
                        .description("Cadastro e gestão de carros e estacionamentos.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Equipe app estacionamentos")
                                .email("suporte@estacionamento.com"))
                );
    }
}
