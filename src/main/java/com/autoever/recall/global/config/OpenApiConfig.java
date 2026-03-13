package com.autoever.recall.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        String sessionCookieName = "JSESSIONID";

        SecurityScheme sessionAuth = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.COOKIE)
                .name(sessionCookieName);

        SecurityRequirement requirement = new SecurityRequirement().addList(sessionCookieName);

        return new OpenAPI()
                .addServersItem(new Server().url("/").description("Default Server"))
                .components(new Components().addSecuritySchemes(sessionCookieName, sessionAuth))
                .addSecurityItem(requirement);
    }
}
