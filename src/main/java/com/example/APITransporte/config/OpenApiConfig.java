package com.example.APITransporte.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Transporte",
                version = "1.0.0",
                description = "Documentación "
        )
)
public class OpenApiConfig {
}
