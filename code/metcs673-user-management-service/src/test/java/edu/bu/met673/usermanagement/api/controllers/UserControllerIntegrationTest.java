package edu.bu.met673.usermanagement.api.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import edu.bu.met673.usermanagement.api.model.GroupDto;
import edu.bu.met673.usermanagement.api.model.UserDto;
import edu.bu.met673.usermanagement.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers

public class UserControllerIntegrationTest {


    @Container
    // @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:latest")
    );

    @LocalServerPort
    private int port;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }


    @Autowired
    private TestRestTemplate restTemplate; // Use TestRestTemplate for HTTP requests

    @Test
    public void testGetCurrentUserProfile() {
        // Make an HTTP GET request to the /me endpoint
        ResponseEntity<UserDto> response = restTemplate.exchange("/me", HttpMethod.GET, null, UserDto.class);

        // Verify the response
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());

    }

    @Test
    void testGetUserProfileById() {
        Long userId = 1L;

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenHolderTest.TOKEN)
                .when()
                .get("/v1/users/{id}", userId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("userId", equalTo(userId.intValue()));
    }

    @Test
    void testCreateUserGroup() {
        UserDto userDto = new UserDto();
        userDto.setId(Long.valueOf("User Id"));
        userDto.setId(Long.valueOf("Test User Description"));

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenHolderTest.TOKEN)
                .body(userDto)
                .when()
                .post("/v1/users/")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo(userDto.getUsername()));
    }
}

