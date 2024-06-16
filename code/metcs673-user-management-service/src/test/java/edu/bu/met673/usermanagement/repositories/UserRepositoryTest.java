package edu.bu.met673.usermanagement.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.bu.met673.usermanagement.entities.User;

@DataJpaTest
//@Import(UserRepository.class)

class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setFirstname("Test");
        user.setLastname("User");
        user.setIdentityProviderId("provider123");
        userRepository.save(user);
    }

    @Test
    void testFindByIdentityProviderId() {
        Optional<User> foundUser = userRepository.findByIdentityProviderId("provider123");
        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
    }

    @Test
    void testFindByIdentityProviderIdNotFound() {
        Optional<User> foundUser = userRepository.findByIdentityProviderId("nonexistent");
        assertFalse(foundUser.isPresent());
    }

    @Test
    void testFindByUsername() {
        User foundUser = userRepository.findByUsername("testuser");
        assertNotNull(foundUser);
        assertEquals("testuser@example.com", foundUser.getEmail());
    }

    @Test
    void testFindByUsernameNotFound() {
        User foundUser = userRepository.findByUsername("unknownuser");
        assertNull(foundUser);
    }

    @Test
    void testFindByEmail() {
        User foundUser = userRepository.findByEmail("testuser@example.com");
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
    }

    @Test
    void testFindByEmailNotFound() {
        User foundUser = userRepository.findByEmail("unknown@example.com");
        assertNull(foundUser);
    }

    @Test
    void testFindUsersWithFilter() {
        Page<User> users = userRepository.findUsers("test", PageRequest.of(0, 10));
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.getTotalElements());
        assertEquals("testuser", users.getContent().get(0).getUsername());
    }

    @Test
    void testFindUsersNoFilter() {
        Page<User> users = userRepository.findUsers(null, PageRequest.of(0, 10));
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.getTotalElements());
        assertEquals("testuser", users.getContent().get(0).getUsername());
    }

    @Test
    void testFindUsersEmptyFilter() {
        Page<User> users = userRepository.findUsers("", PageRequest.of(0, 10));
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.getTotalElements());
        assertEquals("testuser", users.getContent().get(0).getUsername());
    }
}