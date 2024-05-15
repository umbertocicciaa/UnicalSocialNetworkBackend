package com.unicalsocial.backend.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Umberto Domenico Ciccia",
                        email = "umbertociccia@icloud.com"),
                description = "Documentazione backend social network PSW24",
                title = "OpenApi specification - Backend PSW24",
                version = "1.0"
        ),
        servers = {
                @Server(description = "Local", url="http:localhost:8081")
        }
)
@SecurityScheme(
        name="bearAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
