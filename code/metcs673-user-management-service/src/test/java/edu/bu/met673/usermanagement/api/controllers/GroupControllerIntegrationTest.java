package edu.bu.met673.usermanagement.api.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import edu.bu.met673.usermanagement.api.model.GroupDto;
import edu.bu.met673.usermanagement.service.UserGroupService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class GroupControllerIntegrationTest {
	
	@Container
   // @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:latest")
    );

    @LocalServerPort
    private int port;

    @Autowired
    private UserGroupService userGroupService;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }
    
    @Test
    void testCreateUserGroup() {
        GroupDto groupDto = new GroupDto();
        groupDto.setName("Test Group");
        groupDto.setDescription("Test Group Description");

        given()
            .contentType(ContentType.JSON)
            .header("Authorization", "Bearer " + TokenHolderTest.TOKEN)
            .body(groupDto)
        .when()
            .post("/v1/groups/")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("name", equalTo(groupDto.getName()));
    }

    @Test
    void testAddUsersToGroup() {
        List<Long> userIds = Arrays.asList(1L, 2L, 3L);
        Long groupId = 1L;

        given()
            .contentType(ContentType.JSON)
            .header("Authorization", "Bearer " + TokenHolderTest.TOKEN)
            .body(userIds)
        .when()
            .post("/v1/groups/{groupId}/members", groupId)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("$", hasSize(userIds.size()));
    }

    @Test
    void testAddUserToGroup() {
        Long groupId = 1L;
        Long userId = 4L;

        given()
            .contentType(ContentType.JSON)
            .header("Authorization", "Bearer " + TokenHolderTest.TOKEN)
        .when()
            .post("/v1/groups/{groupId}/members/{userId}", groupId, userId)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("userId", equalTo(userId.intValue()));
    }

    @Test
    void testGetGroupById() {
        Long groupId = 1L;

        given()
            .contentType(ContentType.JSON)
            .header("Authorization", "Bearer " + TokenHolderTest.TOKEN)
        .when()
            .get("/v1/groups/{id}", groupId)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("groupId", equalTo(groupId.intValue()));
    }

    @Test
    void testGetMembersForGivenGroup() {
        Long groupId = 1L;

        given()
            .contentType(ContentType.JSON)
            .header("Authorization", "Bearer " + TokenHolderTest.TOKEN)
        .when()
            .get("/v1/groups/{id}/members", groupId)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("$", hasSize(greaterThan(0)));
    }

    @Test
    void testGetAllGroups() {
        given()
            .contentType(ContentType.JSON)
            .header("Authorization", "Bearer " + TokenHolderTest.TOKEN)
        .when()
            .get("/v1/groups/")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("$", hasSize(greaterThan(0)));
    }

    
}