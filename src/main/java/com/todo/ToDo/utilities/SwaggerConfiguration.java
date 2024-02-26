package com.todo.ToDo.utilities;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("ToDo App Backend").version("0.0.1").description("This API use for demo purpose")
                        .contact(new Contact().name("Sojib").url("https://simec-inc.net/").email("sojib.19991018@gmail.com")));
    }

}