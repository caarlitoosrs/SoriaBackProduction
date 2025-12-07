package com.experienciassoria.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .info(new Info()
                                                .title("Experiencias Soria API")
                                                .version("1.0.0")
                                                .description("Documentación del backend de Experiencias Soria")
                                                .contact(new Contact()
                                                                .name("Equipo Experiencias Soria")
                                                                .email("soporte@experienciassoria.com"))
                                                .license(new License().name("Apache 2.0")
                                                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
        }
}
