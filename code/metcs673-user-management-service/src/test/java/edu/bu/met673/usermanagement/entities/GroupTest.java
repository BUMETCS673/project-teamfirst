package edu.bu.met673.usermanagement.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import edu.bu.met673.usermanagement.api.model.UserRole;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {Group.class})
@ExtendWith(SpringExtension.class)
class GroupTest {
    @Autowired
    private Group group;

    /**
     * Method under test: {@link Group#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new Group()).canEqual("Other"));
    }

    /**
     * Method under test: {@link Group#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        Group group2 = new Group();

        Group group3 = new Group();
        group3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group3.setDescription("The characteristics of someone or something");
        group3.setName("Name");
        group3.setUserGroups(new ArrayList<>());

        // Act and Assert
        assertTrue(group2.canEqual(group3));
    }

    /**
     * Method under test: {@link Group#canEqual(Object)}
     */
    @Test
    void testCanEqual3() {
        // Arrange
        Group group2 = new Group();

        Group group3 = new Group();
        group3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group3.setDescription("The characteristics of someone or something");
        group3.setName("Name");
        group3.setUserGroups(new ArrayList<>());

        User user = new User();
        user.setAddress("42 Main St");
        user.setCity("Oxford");
        user.setCountry("GB");
        user.setEmail("jane.doe@example.org");
        user.setFirstname("Jane");
        user.setIdentityProviderId("42");
        user.setLastname("Doe");
        user.setPhone("6625550144");
        user.setPostalCode("Postal Code");
        user.setState("MD");
        user.setUserGroup(new ArrayList<>());
        user.setUserRole(UserRole.ADMIN);
        user.setUsername("janedoe");

        UserGroup userGroup = new UserGroup();
        userGroup.setGorupRole("Gorup Role");
        userGroup.setGroup(group3);
        userGroup.setId(1L);
        userGroup.setJoinedAt(mock(Timestamp.class));
        userGroup.setUser(user);

        ArrayList<UserGroup> userGroups = new ArrayList<>();
        userGroups.add(userGroup);

        Group group4 = new Group();
        group4.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group4.setDescription("The characteristics of someone or something");
        group4.setName("Name");
        group4.setUserGroups(userGroups);

        // Act and Assert
        assertTrue(group2.canEqual(group4));
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Group#equals(Object)}
     *   <li>{@link Group#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
        // Arrange
        Group group = new Group();
        group.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group.setDescription("The characteristics of someone or something");
        group.setName("Name");
        group.setUserGroups(new ArrayList<>());

        Group group2 = new Group();
        group2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group2.setDescription("The characteristics of someone or something");
        group2.setName("Name");
        group2.setUserGroups(new ArrayList<>());

        // Act and Assert
        assertEquals(group, group2);
        int expectedHashCodeResult = group.hashCode();
        assertEquals(expectedHashCodeResult, group2.hashCode());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Group#equals(Object)}
     *   <li>{@link Group#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
        // Arrange
        Group group = new Group();
        group.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group.setDescription("The characteristics of someone or something");
        group.setName("Name");
        group.setUserGroups(new ArrayList<>());

        // Act and Assert
        assertEquals(group, group);
        int expectedHashCodeResult = group.hashCode();
        assertEquals(expectedHashCodeResult, group.hashCode());
    }

    /**
     * Method under test: {@link Group#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
        // Arrange
        Group group = new Group();
        group.setCreatedBy("Name");
        group.setDescription("The characteristics of someone or something");
        group.setName("Name");
        group.setUserGroups(new ArrayList<>());

        Group group2 = new Group();
        group2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group2.setDescription("The characteristics of someone or something");
        group2.setName("Name");
        group2.setUserGroups(new ArrayList<>());

        // Act and Assert
        assertNotEquals(group, group2);
    }

    /**
     * Method under test: {@link Group#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
        // Arrange
        Group group = new Group();
        group.setCreatedBy(null);
        group.setDescription("The characteristics of someone or something");
        group.setName("Name");
        group.setUserGroups(new ArrayList<>());

        Group group2 = new Group();
        group2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group2.setDescription("The characteristics of someone or something");
        group2.setName("Name");
        group2.setUserGroups(new ArrayList<>());

        // Act and Assert
        assertNotEquals(group, group2);
    }

    /**
     * Method under test: {@link Group#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
        // Arrange
        Group group = new Group();
        group.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group.setDescription("Name");
        group.setName("Name");
        group.setUserGroups(new ArrayList<>());

        Group group2 = new Group();
        group2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group2.setDescription("The characteristics of someone or something");
        group2.setName("Name");
        group2.setUserGroups(new ArrayList<>());

        // Act and Assert
        assertNotEquals(group, group2);
    }

    /**
     * Method under test: {@link Group#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
        // Arrange
        Group group = new Group();
        group.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group.setDescription(null);
        group.setName("Name");
        group.setUserGroups(new ArrayList<>());

        Group group2 = new Group();
        group2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group2.setDescription("The characteristics of someone or something");
        group2.setName("Name");
        group2.setUserGroups(new ArrayList<>());

        // Act and Assert
        assertNotEquals(group, group2);
    }

    /**
     * Method under test: {@link Group#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
        // Arrange
        Group group = new Group();
        group.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group.setDescription("The characteristics of someone or something");
        group.setName("The characteristics of someone or something");
        group.setUserGroups(new ArrayList<>());

        Group group2 = new Group();
        group2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group2.setDescription("The characteristics of someone or something");
        group2.setName("Name");
        group2.setUserGroups(new ArrayList<>());

        // Act and Assert
        assertNotEquals(group, group2);
    }

    /**
     * Method under test: {@link Group#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
        // Arrange
        Group group = new Group();
        group.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group.setDescription("The characteristics of someone or something");
        group.setName(null);
        group.setUserGroups(new ArrayList<>());

        Group group2 = new Group();
        group2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group2.setDescription("The characteristics of someone or something");
        group2.setName("Name");
        group2.setUserGroups(new ArrayList<>());

        // Act and Assert
        assertNotEquals(group, group2);
    }

    /**
     * Method under test: {@link Group#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
        // Arrange
        Group group = new Group();
        group.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group.setDescription("The characteristics of someone or something");
        group.setName("Name");
        group.setUserGroups(new ArrayList<>());

        User user = new User();
        user.setAddress("42 Main St");
        user.setCity("Oxford");
        user.setCountry("GB");
        user.setEmail("jane.doe@example.org");
        user.setFirstname("Jane");
        user.setIdentityProviderId("42");
        user.setLastname("Doe");
        user.setPhone("6625550144");
        user.setPostalCode("Name");
        user.setState("MD");
        user.setUserGroup(new ArrayList<>());
        user.setUserRole(UserRole.ADMIN);
        user.setUsername("janedoe");

        UserGroup userGroup = new UserGroup();
        userGroup.setGorupRole("Name");
        userGroup.setGroup(group);
        userGroup.setId(1L);
        userGroup.setJoinedAt(mock(Timestamp.class));
        userGroup.setUser(user);

        ArrayList<UserGroup> userGroups = new ArrayList<>();
        userGroups.add(userGroup);

        Group group2 = new Group();
        group2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group2.setDescription("The characteristics of someone or something");
        group2.setName("Name");
        group2.setUserGroups(userGroups);

        Group group3 = new Group();
        group3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group3.setDescription("The characteristics of someone or something");
        group3.setName("Name");
        group3.setUserGroups(new ArrayList<>());

        // Act and Assert
        assertNotEquals(group2, group3);
    }

    /**
     * Method under test: {@link Group#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsNull_thenReturnNotEqual() {
        // Arrange
        Group group = new Group();
        group.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group.setDescription("The characteristics of someone or something");
        group.setName("Name");
        group.setUserGroups(new ArrayList<>());

        // Act and Assert
        assertNotEquals(group, null);
    }

    /**
     * Method under test: {@link Group#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
        // Arrange
        Group group = new Group();
        group.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        group.setDescription("The characteristics of someone or something");
        group.setName("Name");
        group.setUserGroups(new ArrayList<>());

        // Act and Assert
        assertNotEquals(group, "Different type to Group");
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>default or parameterless constructor of {@link Group}
     *   <li>{@link Group#setCreatedBy(String)}
     *   <li>{@link Group#setDescription(String)}
     *   <li>{@link Group#setName(String)}
     *   <li>{@link Group#setUserGroups(List)}
     *   <li>{@link Group#toString()}
     *   <li>{@link Group#getCreatedBy()}
     *   <li>{@link Group#getDescription()}
     *   <li>{@link Group#getName()}
     *   <li>{@link Group#getUserGroups()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Group actualGroup = new Group();
        actualGroup.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        actualGroup.setDescription("The characteristics of someone or something");
        actualGroup.setName("Name");
        ArrayList<UserGroup> userGroups = new ArrayList<>();
        actualGroup.setUserGroups(userGroups);
        String actualToStringResult = actualGroup.toString();
        String actualCreatedBy = actualGroup.getCreatedBy();
        String actualDescription = actualGroup.getDescription();
        String actualName = actualGroup.getName();

        // Assert that nothing has changed
        assertEquals(
                "Group(name=Name, description=The characteristics of someone or something, createdBy=Jan 1, 2020 8:00am"
                        + " GMT+0100, userGroups=[])",
                actualToStringResult);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Name", actualName);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertSame(userGroups, actualGroup.getUserGroups());
    }
}
