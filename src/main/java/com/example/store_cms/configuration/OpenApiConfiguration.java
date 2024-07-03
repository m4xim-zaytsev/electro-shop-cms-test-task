package com.example.store_cms.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openApiDescription(){
        Server localhostServer = new Server();
        localhostServer.setUrl("http://localhost:8080");
        localhostServer.setDescription("local env");

        Server productionServer = new Server();
        productionServer.setUrl("http://some.prod.url");
        productionServer.setDescription("production env");

        Contact contact = new Contact();
        contact.setName("Maxim Zaitsev");
        contact.setEmail("m4ximzaystev@gmail.com");
        contact.setUrl("http://some.url");

        License mitLicence = new License().name("GNU AGPLv3")
                .url("https://choosealicnese.com/licesnse/aqpl-3.0");

        Info info = new Info()
                .title("store cms API")
                .version("1.0")
                .contact(contact)
                .description("API for store cms")
                .termsOfService("http://some.terms.url")
                .license(mitLicence);

        return new OpenAPI().info(info).servers(List.of(localhostServer,productionServer));
    }
}
