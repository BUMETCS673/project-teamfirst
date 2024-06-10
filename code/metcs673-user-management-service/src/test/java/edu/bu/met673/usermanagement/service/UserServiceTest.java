/**
 * 
 */
package edu.bu.met673.usermanagement.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.bu.met673.usermanagement.api.model.UserRegistrationRequest;
import edu.bu.met673.usermanagement.auth0.client.Auth0Client;
import edu.bu.met673.usermanagement.exceptions.InvalidUserAccountException;
import edu.bu.met673.usermanagement.mapper.UserMapper;
import edu.bu.met673.usermanagement.repositories.UserRepository;
import edu.bu.met673.usermanagement.service.impl.UsersServiceImpl;

/**
 * 
 */
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	private UserService userService;
    @Mock private UserRepository userRepository;
    @Mock private Auth0Client auth0Client;
    @Mock private UserMapper mapper;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	userService = new UsersServiceImpl(userRepository, auth0Client, mapper);
    }
	
    @Test
    void testRegisterUserInvalidAccount() {
        when(auth0Client.getUserProfile(anyString())).thenReturn(null);
        UserRegistrationRequest userDto = new UserRegistrationRequest();

        assertThrows(InvalidUserAccountException.class, () -> {
            userService.register(userDto);
        });
    }

}
