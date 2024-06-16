package edu.bu.met673.usermanagement.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import edu.bu.met673.usermanagement.api.model.GroupDto;
import edu.bu.met673.usermanagement.api.model.UserGroupDto;
import edu.bu.met673.usermanagement.entities.Group;
import edu.bu.met673.usermanagement.entities.User;
import edu.bu.met673.usermanagement.entities.UserGroup;

class UserGroupMapperTest {

    @Test
    void testToGroupDto() {
        // Arrange
        Group group = new Group();
        group.setId(1L);
        group.setName("Test Group");
        group.setDescription("This is a test group");
        group.setCreatedBy("admin");

        // Act
        GroupDto groupDto = UserGroupMapper.toGroupDto(group);

        // Assert
        assertNotNull(groupDto);
        assertEquals(group.getId(), groupDto.getGroupId());
        assertEquals(group.getName(), groupDto.getName());
        assertEquals(group.getDescription(), groupDto.getDescription());
        assertEquals(group.getCreatedBy(), groupDto.getCreatedBy());
    }

    @Test
    void testToGroupDto_Null() {
        // Act
        GroupDto groupDto = UserGroupMapper.toGroupDto(null);

        // Assert
        assertNull(groupDto);
    }

    @Test
    void testToGroupEntity() {
        // Arrange
        GroupDto groupDto = new GroupDto();
        groupDto.setName("Test Group");
        groupDto.setDescription("This is a test group");
        groupDto.setCreatedBy("admin");

        // Act
        Group group = UserGroupMapper.toGroupEntity(groupDto);

        // Assert
        assertNotNull(group);
        assertEquals(groupDto.getName(), group.getName());
        assertEquals(groupDto.getDescription(), group.getDescription());
        assertEquals(groupDto.getCreatedBy(), group.getCreatedBy());
    }

    @Test
    void testToGroupEntity_Null() {
        // Act
        Group group = UserGroupMapper.toGroupEntity(null);

        // Assert
        assertNull(group);
    }

    @Test
    void testToUserGroupDto() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setFirstname("John");
        user.setLastname("Doe");

        Group group = new Group();
        group.setId(1L);
        group.setName("Test Group");

        UserGroup userGroup = new UserGroup();
       // userGroup.setJoinedAt(new Time);
        userGroup.setGorupRole("Member");
        userGroup.setUser(user);
        userGroup.setGroup(group);

        // Act
        UserGroupDto userGroupDto = UserGroupMapper.toUserGroupDto(userGroup);

        // Assert
        assertNotNull(userGroupDto);
        assertEquals(userGroup.getJoinedAt(), userGroupDto.getJoinedAt());
        assertEquals(userGroup.getGorupRole(), userGroupDto.getGorupRole());
        assertEquals(group.getName(), userGroupDto.getGroup().getName());
    }

    @Test
    void testToUserGroupDto_Null() {
        // Act
        UserGroupDto userGroupDto = UserGroupMapper.toUserGroupDto(null);

        // Assert
        assertNull(userGroupDto);
    }

    @Test
    void testToUserGroupEntity() {
        // Arrange
        UserGroupDto userGroupDto = new UserGroupDto();
       // userGroupDto.setJoinedAt(LocalDateTime.now().to);
        userGroupDto.setGorupRole("Member");

        // Act
        UserGroup userGroup = UserGroupMapper.toUserGroupEntity(userGroupDto);

        // Assert
        assertNotNull(userGroup);
        assertEquals(userGroupDto.getJoinedAt(), userGroup.getJoinedAt());
        assertEquals(userGroupDto.getGorupRole(), userGroup.getGorupRole());
    }

    @Test
    void testToUserGroupEntity_Null() {
        // Act
        UserGroup userGroup = UserGroupMapper.toUserGroupEntity(null);

        // Assert
        assertNull(userGroup);
    }
}
