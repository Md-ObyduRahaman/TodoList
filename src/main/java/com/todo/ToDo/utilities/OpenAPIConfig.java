package com.todo.ToDo.utilities;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Value("${bezkoder.openapi.dev-url}")
    private String devUrl;

    @Value("${bezkoder.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("sojib.19991018@gmail.com");
        contact.setName("Sojib");
        contact.setUrl("https://www.sojib.com");

        License mitLicense = new License().name("MIT License").url("https://sojib.com/licenses/mit/");

        Info info = new Info()
                .title("Task Management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage Task.").termsOfService("https://www.Sojib.com/terms")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}

