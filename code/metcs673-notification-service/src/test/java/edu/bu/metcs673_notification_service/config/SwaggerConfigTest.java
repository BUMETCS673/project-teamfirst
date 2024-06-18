package edu.bu.metcs673_notification_service.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SwaggerConfigTest {

    @Autowired
    private OpenAPI openAPI;

    @Test
    void testCustomOpenAPI() {
        assertNotNull(openAPI, "OpenAPI bean should not be null");

        // Verify Info
        Info info = openAPI.getInfo();
        assertNotNull(info, "Info should not be null");
        assertEquals("Notification Service API", info.getTitle());
        assertEquals("1.0", info.getVersion());
        assertEquals("#", info.getTermsOfService());

        // Verify Contact
        Contact contact = info.getContact();
        assertNotNull(contact, "Contact should not be null");
        assertEquals("Support Team - METCS 673 Team1", contact.getName());
        assertEquals("lsxcs@bu.edu", contact.getEmail());
        assertEquals("#", contact.getUrl());

        // Verify License
        License license = info.getLicense();
        assertNotNull(license, "License should not be null");
        assertEquals("Apache 2.0", license.getName());
        assertEquals("bu.edu", license.getUrl());

        // Verify Security Schemes
        assertNotNull(openAPI.getComponents(), "Components should not be null");
        SecurityScheme securityScheme = openAPI.getComponents().getSecuritySchemes().get("bearerAuth");
        assertNotNull(securityScheme, "SecurityScheme should not be null");
        assertEquals(SecurityScheme.Type.HTTP, securityScheme.getType());
        assertEquals("bearer", securityScheme.getScheme());
        assertEquals("JWT", securityScheme.getBearerFormat());
        assertEquals(SecurityScheme.In.HEADER, securityScheme.getIn());

        // Verify Security Requirements
        assertNotNull(openAPI.getSecurity(), "Security requirements should not be null");
        assertFalse(openAPI.getSecurity().isEmpty(), "Security requirements should not be empty");
        SecurityRequirement securityRequirement = openAPI.getSecurity().get(0);
        assertTrue(securityRequirement.containsKey("bearerAuth"), "SecurityRequirement should contain bearerAuth");
    }

    // Import the configuration to be tested
    @Configuration
    @Import(SwaggerConfig.class)
    static class TestConfig {
    }
}