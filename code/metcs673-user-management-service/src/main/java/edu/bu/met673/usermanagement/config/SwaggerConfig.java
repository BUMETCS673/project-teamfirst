package edu.bu.met673.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    String schemeName = "bearerAuth";
    String bearerFormat = "JWT";
    String scheme = "bearer";

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(schemeName))
                .components(new Components().addSecuritySchemes(schemeName,
                        new SecurityScheme().name(schemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .bearerFormat(bearerFormat)
                                .in(SecurityScheme.In.HEADER)
                                .scheme(scheme)))
                .info(new Info()
                        .title("User and Group Management Service API")
                        .description("""
                            The User and Group Management Service API provides a comprehensive set of endpoints for managing users and their group memberships.
                            With this API, you can retrieve individual user profiles, register new users, manage group memberships, and list users and groups with advanced filtering and pagination capabilities.
                            
                            Security is a critical aspect of this API, which integrates with Auth0 to leverage OAuth 2.0 for authentication and authorization.
                            All API operations require appropriate user permissions and are protected using JWT tokens issued by Auth0.
                            This ensures that only authenticated and authorized users can perform operations.
                            """)
                        .version("1.0")
                        .termsOfService("#")
                        .contact(new Contact()
                            .name("Support Team - METCS 673 Team1")
                            .email("ajordany@bu.edu")
                            .url("#"))
                        .license(new License()
                            .name("Apache 2.0")
                            .url("bu.edu")));
    }
}