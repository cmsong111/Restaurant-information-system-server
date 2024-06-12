package com.galaxy.Restaurantinformationsystem.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(title = "Restaurant information system", version = "1.1(beta)",
        description = "Restaurant information system server API",
        contact = @Contact(name = "김남주", email = "cmsong111@gmail.com")))

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        // SecuritySecheme명
        String jwtSchemeName = "JSON Web Token (JWT)";

        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);

        // API 문서에 적용할 SecurityScheme
        Components components = new Components().addSecuritySchemes(jwtSchemeName,
                new SecurityScheme().name(jwtSchemeName).type(SecurityScheme.Type.HTTP) // HTTP 방식
                        .scheme("bearer").bearerFormat("JWT")); // 토큰 형식을 지정하는 임의의 문자(Optional)

        return new OpenAPI().addSecurityItem(securityRequirement).components(components);

    }
}
