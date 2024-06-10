/**
 * 
 */
/*package edu.bu.met673.usermanagement.api.controllers;


import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import edu.bu.met673.usermanagement.api.model.UserDto;
import edu.bu.met673.usermanagement.exceptions.InvalidUserAccountException;
import edu.bu.met673.usermanagement.exceptions.UserProfileAlreadyExistsException;
import edu.bu.met673.usermanagement.service.UserService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.mockmvc.response.MockMvcResponse;

public class UserRegistrationSteps {

    @Autowired
    private UserService userService;

    @MockBean
    private UserController userController;

    private MockMvcResponse response;
    private UserDto userDto;

    @Given("a user with username {string} and email {string}")
    public void a_user_with_username_and_email(String username, String email) {
        userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setEmail(email);
    }

    @Given("an invalid user account")
    public void an_invalid_user_account() {
        when(userService.register(any(UserDto.class)))
                .thenThrow(new InvalidUserAccountException("Invalid user account"));
    }

    @Given("a user with the same username or email already exists")
    public void a_user_with_the_same_username_or_email_already_exists() {
        when(userService.register(any(UserDto.class)))
                .thenThrow(new UserProfileAlreadyExistsException("User profile already exists"));
    }

    @When("I send a registration request")
    public void i_send_a_registration_request() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(userDto)
                .when()
                .put("/v1/users/register");
    }

    @Then("the user should be registered successfully")
    public void the_user_should_be_registered_successfully() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body("username", equalTo(userDto.getUsername()))
                .body("email", equalTo(userDto.getEmail()));
    }

    @Then("I should receive an {string} error")
    public void i_should_receive_an_error(String errorMessage) {
        response.then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo(errorMessage));
    }
}*/
