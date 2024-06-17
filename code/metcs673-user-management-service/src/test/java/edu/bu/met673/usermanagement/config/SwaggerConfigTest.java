package edu.bu.met673.usermanagement.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SwaggerConfig.class})
@ExtendWith(SpringExtension.class)
class SwaggerConfigTest {
    @Autowired
    private SwaggerConfig swaggerConfig;

    /**
     * Method under test: {@link SwaggerConfig#customOpenAPI()}
     */
    @Test
    void testCustomOpenAPI() {


        // Arrange and Act
        OpenAPI actualCustomOpenAPIResult = (new SwaggerConfig()).customOpenAPI();

        // Assert
        Info info = actualCustomOpenAPIResult.getInfo();
        Contact contact = info.getContact();
        assertEquals("#", contact.getUrl());
        assertEquals("#", info.getTermsOfService());
        assertEquals("1.0", info.getVersion());
        assertEquals("3.0.1", actualCustomOpenAPIResult.getOpenapi());
        License license = info.getLicense();
        assertEquals("Apache 2.0", license.getName());
        assertEquals("Support Team - METCS 673 Team1", contact.getName());
        assertEquals(
                "The User and Group Management Service API provides a comprehensive set of endpoints for managing users"
                        + " and their group memberships.\n"
                        + "With this API, you can retrieve individual user profiles, register new users, manage group memberships,"
                        + " and list users and groups with advanced filtering and pagination capabilities.\n" + "\n"
                        + "Security is a critical aspect of this API, which integrates with Auth0 to leverage OAuth 2.0 for"
                        + " authentication and authorization.\n"
                        + "All API operations require appropriate user permissions and are protected using JWT tokens issued"
                        + " by Auth0.\n" + "This ensures that only authenticated and authorized users can perform operations.\n",
                info.getDescription());
        assertEquals("User and Group Management Service API", info.getTitle());
        assertEquals("ajordany@bu.edu", contact.getEmail());
        assertEquals("bu.edu", license.getUrl());
        assertNull(actualCustomOpenAPIResult.getExternalDocs());
        assertNull(actualCustomOpenAPIResult.getPaths());
        assertNull(info.getSummary());
        assertNull(license.getIdentifier());
        assertNull(actualCustomOpenAPIResult.getServers());
        assertNull(actualCustomOpenAPIResult.getTags());
        Components components = actualCustomOpenAPIResult.getComponents();
        assertNull(components.getPathItems());
        assertNull(actualCustomOpenAPIResult.getWebhooks());
        assertNull(components.getCallbacks());
        assertNull(components.getExamples());
        assertNull(components.getHeaders());
        assertNull(components.getLinks());
        assertNull(components.getSchemas());
        assertNull(components.getParameters());
        assertNull(components.getRequestBodies());
        assertNull(components.getResponses());
        assertNull(components.getExtensions());
        assertNull(actualCustomOpenAPIResult.getExtensions());
        assertNull(contact.getExtensions());
        assertNull(info.getExtensions());
        assertNull(license.getExtensions());
        assertEquals(1, actualCustomOpenAPIResult.getSecurity().size());
        assertEquals(1, components.getSecuritySchemes().size());
        assertEquals(SpecVersion.V30, actualCustomOpenAPIResult.getSpecVersion());
    }

}
