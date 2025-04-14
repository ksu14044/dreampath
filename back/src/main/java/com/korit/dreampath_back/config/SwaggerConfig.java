package com.korit.dreampath_back.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI() {
        OpenAPI openAPI = new OpenAPI();
        openAPI.info(getInfo());
        openAPI.addSecurityItem(getSecurityRequirement());
        openAPI.components(new Components().addSecuritySchemes(
                "Bearer Authentication",
                new SecurityScheme()
                        .name("Bearer Authentication")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
        ));
        return openAPI;
    }

    private Info getInfo() {
        Info info = new Info();
        info.title("DreamPath Project");
        info.version("1.0.0");
        info.description("프로젝트 스웨거입니다.");
        info.contact(getContact());
        return info;
    }

    private Contact getContact() {
        Contact contact = new Contact();
        contact.name("김시욱, 홍문일, 김선혜, 차정민, 김수환");
        contact.email("mhm1404@naver.com");
        return contact;
    }


    private SecurityRequirement getSecurityRequirement() {
        return new SecurityRequirement().addList("Bearer Authentication");
    }


}
