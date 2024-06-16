package edu.bu.metcs673_notification_service.config;

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
                        .title("Notification Service API")
                        .description("""
                            The Notification Service API provides sets of endpoint for send notifications to registered users.
                            With these API, you can send notifications, and track delivery status.
                            
                            Security is a critical aspect of this API, which integrates with Auth0 to leverage OAuth 2.0 for authentication and authorization.
                            All API operations require appropriate user permissions and are protected using JWT tokens issued by Auth0.
                            This ensures that only authenticated and authorized users can perform operations.
                            """)
                        .version("1.0")
                        .termsOfService("#")
                        .contact(new Contact()
                                .name("Support Team - METCS 673 Team1")
                                .email("lsxcs@bu.edu")
                                .url("#"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("bu.edu")));
    }
}
