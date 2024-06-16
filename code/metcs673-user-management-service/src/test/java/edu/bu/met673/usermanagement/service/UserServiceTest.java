package edu.bu.met673.usermanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import edu.bu.met673.usermanagement.api.model.PageData;
import edu.bu.met673.usermanagement.api.model.UserDto;
import edu.bu.met673.usermanagement.api.model.UserRegistrationRequest;
import edu.bu.met673.usermanagement.auth0.client.Auth0Client;
import edu.bu.met673.usermanagement.auth0.client.Auth0User;
import edu.bu.met673.usermanagement.entities.User;
import edu.bu.met673.usermanagement.exceptions.InvalidUserAccountException;
import edu.bu.met673.usermanagement.exceptions.ResourceNotFoundException;
import edu.bu.met673.usermanagement.exceptions.UserProfileAlreadyExistsException;
import edu.bu.met673.usermanagement.mapper.UserMapper;
import edu.bu.met673.usermanagement.repositories.UserRepository;
import edu.bu.met673.usermanagement.service.impl.UsersServiceImpl;

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

    @Test
    void testRegisterUserAlreadyExists() {
        Auth0User auth0User = new Auth0User();
        auth0User.setSub("auth0|12345");
        when(auth0Client.getUserProfile(anyString())).thenReturn(auth0User);
        when(userRepository.findByIdentityProviderId(anyString())).thenReturn(Optional.of(new User()));

        UserRegistrationRequest userDto = new UserRegistrationRequest();
        userDto.setUsername("existinguser");
        userDto.setEmail("existing@domain.com");

        assertThrows(UserProfileAlreadyExistsException.class, () -> {
            userService.register(userDto);
        });
    }

    @Test
    void testRegisterUserSuccess() {
        Auth0User auth0User = new Auth0User();
        auth0User.setSub("auth0|12345");
        when(auth0Client.getUserProfile(anyString())).thenReturn(auth0User);
        when(userRepository.findByIdentityProviderId(anyString())).thenReturn(null);
        when(userRepository.findByUsername(anyString())).thenReturn(null);
        when(userRepository.findByEmail(anyString())).thenReturn(null);

        User user = new User();
        user.setId(1L);
        UserDto userDto = new UserDto();
        userDto.setId(1L);

        when(mapper.toEntity(any(UserRegistrationRequest.class))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(mapper.toDto(any(User.class))).thenReturn(userDto);

        UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
        registrationRequest.setUsername("newuser");
        registrationRequest.setEmail("new@domain.com");

        UserDto result = userService.register(registrationRequest);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetMyProfile() {
        Auth0User auth0User = new Auth0User();
        auth0User.setSub("auth0|12345");
        when(auth0Client.getUserProfile(anyString())).thenReturn(auth0User);

        User user = new User();
        user.setIdentityProviderId("auth0|12345");
        user.setId(1L);
        when(userRepository.findByIdentityProviderId(anyString())).thenReturn(Optional.of(user));

        UserDto userDto = new UserDto();
        userDto.setId(1L);
        when(mapper.toDto(any(User.class))).thenReturn(userDto);

        UserDto result = userService.getMyProfile();
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetMyProfileInvalidAccount() {
        when(auth0Client.getUserProfile(anyString())).thenReturn(null);

        assertThrows(InvalidUserAccountException.class, () -> {
            userService.getMyProfile();
        });
    }

    @Test
    void testGetUserProfileSuccess() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        UserDto userDto = new UserDto();
        userDto.setId(1L);
        when(mapper.toDto(any(User.class))).thenReturn(userDto);

        UserDto result = userService.getUserProfile(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetUserProfileNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.getUserProfile(1L);
        });
    }

    @Test
    void testGetAllUserProfiles() {
        Page<User> page = new PageImpl<>(Collections.singletonList(new User()));
        when(userRepository.findUsers(anyString(), any(PageRequest.class))).thenReturn(page);

        PageData<UserDto> result = userService.getAllUserProfiles("filter", PageRequest.of(0, 10));
        assertNotNull(result);
        assertFalse(result.getContent().isEmpty());
    }

    @Test
    void testUpdateUserProfile() {
        Auth0User auth0User = new Auth0User();
        auth0User.setSub("auth0|12345");
        when(auth0Client.getUserProfile(anyString())).thenReturn(auth0User);

        User user = new User();
        user.setId(1L);
        user.setIdentityProviderId("auth0|12345");
        when(userRepository.findByIdentityProviderId(anyString())).thenReturn(Optional.of(user));

        UserDto userDto = new UserDto();
        userDto.setId(1L);
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername("updateduser");
        request.setEmail("updated@domain.com");

        when(mapper.toEntity(any(UserRegistrationRequest.class))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(mapper.toDto(any(User.class))).thenReturn(userDto);

        UserDto result = userService.updateUserProfile(1L, request);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testUpdateUserProfileInvalidAccount() {
        when(auth0Client.getUserProfile(anyString())).thenReturn(null);

        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername("updateduser");
        request.setEmail("updated@domain.com");

        assertThrows(InvalidUserAccountException.class, () -> {
            userService.updateUserProfile(1L, request);
        });
    }
}