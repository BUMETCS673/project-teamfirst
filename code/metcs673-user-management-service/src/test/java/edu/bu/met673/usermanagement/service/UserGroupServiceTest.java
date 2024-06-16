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
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.bu.met673.usermanagement.api.model.GroupDto;
import edu.bu.met673.usermanagement.api.model.UserGroupDto;
import edu.bu.met673.usermanagement.api.model.UserSummaryDto;
import edu.bu.met673.usermanagement.entities.Group;
import edu.bu.met673.usermanagement.entities.User;
import edu.bu.met673.usermanagement.entities.UserGroup;
import edu.bu.met673.usermanagement.exceptions.ResourceNotFoundException;
import edu.bu.met673.usermanagement.exceptions.ServiceException;
import edu.bu.met673.usermanagement.repositories.GroupRepository;
import edu.bu.met673.usermanagement.repositories.UserGroupRepository;
import edu.bu.met673.usermanagement.repositories.UserRepository;
import edu.bu.met673.usermanagement.service.impl.UserGroupServiceImpl;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class UserGroupServiceTest {

    private UserGroupService userGroupService;

    @Mock private GroupRepository groupRepository;
    @Mock private UserGroupRepository userGroupRepository;
    @Mock private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userGroupService = new UserGroupServiceImpl(groupRepository, userGroupRepository, userRepository);
    }

    @Test
    void testCreateGroupSuccess() {
        GroupDto groupDto = new GroupDto();
        groupDto.setName("Test Group");
        groupDto.setDescription("A group for testing");
        groupDto.setCreatedBy("Admin");

        Group group = new Group();
        group.setId(1L);
        group.setName("Test Group");
        group.setDescription("A group for testing");
        group.setCreatedBy("Admin");

        when(groupRepository.save(any(Group.class))).thenReturn(group);

        GroupDto result = userGroupService.createGroup(groupDto);
        assertNotNull(result);
        assertEquals(1L, result.getGroupId());
        assertEquals("Test Group", result.getName());
        assertEquals("A group for testing", result.getDescription());
        assertEquals("Admin", result.getCreatedBy());
    }

    @Test
    void testCreateGroupServiceException() {
        GroupDto groupDto = new GroupDto();
        groupDto.setName("Test Group");

        when(groupRepository.save(any(Group.class))).thenThrow(new RuntimeException("Database Error"));

        assertThrows(ServiceException.class, () -> {
            userGroupService.createGroup(groupDto);
        });
    }

    @Test
    void testDeleteGroupSuccess() {
        Group group = new Group();
        group.setId(1L);
        group.setName("Test Group");

        when(groupRepository.findById(anyLong())).thenReturn(Optional.of(group));
        GroupDto result = userGroupService.deleteGroup(1L);
        assertNotNull(result);
        assertEquals(1L, result.getGroupId());
    }

    @Test
    void testDeleteGroupResourceNotFoundException() {
        when(groupRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userGroupService.deleteGroup(1L);
        });
    }

    @Test
    void testSearchGroupsNoFilter() {
        Group group = new Group();
        group.setId(1L);
        group.setName("Test Group");
        group.setDescription("A group for testing");
        group.setCreatedBy("Admin");

        List<Group> groups = Collections.singletonList(group);

        when(groupRepository.findAll()).thenReturn(groups);

        List<GroupDto> result = userGroupService.searchGroups("");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getGroupId());
        assertEquals("Test Group", result.get(0).getName());
    }

    @Test
    void testSearchGroupsWithFilter() {
        Group group = new Group();
        group.setId(1L);
        group.setName("Filtered Group");
        group.setDescription("A filtered group");
        group.setCreatedBy("Admin");

        List<Group> groups = Collections.singletonList(group);

        when(groupRepository.search(anyString())).thenReturn(groups);

        List<GroupDto> result = userGroupService.searchGroups("filter");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getGroupId());
        assertEquals("Filtered Group", result.get(0).getName());
    }

    @Test
    void testSearchGroupsServiceException() {
        when(groupRepository.findAll()).thenThrow(new RuntimeException("Database Error"));

        assertThrows(ServiceException.class, () -> {
            userGroupService.searchGroups("");
        });
    }

    @Test
    void testGetGroupByIdSuccess() {
        Group group = new Group();
        group.setId(1L);
        group.setName("Test Group");
        group.setDescription("A group for testing");
        group.setCreatedBy("Admin");

        when(groupRepository.findById(anyLong())).thenReturn(Optional.of(group));

        GroupDto result = userGroupService.getGroupById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getGroupId());
        assertEquals("Test Group", result.getName());
    }

    @Test
    void testGetGroupByIdNotFound() {
        when(groupRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userGroupService.getGroupById(1L);
        });
    }

    @Test
    void testAddUserToGroupSuccess() {
        Group group = new Group();
        group.setId(1L);

        User user = new User();
        user.setId(1L);

        UserGroup userGroup = new UserGroup();
        userGroup.setUser(user);
        userGroup.setGroup(group);

        when(groupRepository.findById(anyLong())).thenReturn(Optional.of(group));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userGroupRepository.save(any(UserGroup.class))).thenReturn(userGroup);

        UserGroupDto result = userGroupService.addUserToGroup(1L, 1L);
        assertNotNull(result);
        assertEquals(1L, result.getGroup().getGroupId());
        assertEquals(1L, result.getUser().getId());
    }

    @Test
    void testAddUserToGroupGroupNotFound() {
        when(groupRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userGroupService.addUserToGroup(1L, 1L);
        });
    }

    @Test
    void testAddUserToGroupUserNotFound() {
        Group group = new Group();
        group.setId(1L);

        when(groupRepository.findById(anyLong())).thenReturn(Optional.of(group));
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userGroupService.addUserToGroup(1L, 1L);
        });
    }

    @Test
    void testGetMembersForGivenGroupSuccess() {
        User user = new User();
        user.setId(1L);
        
        Group group = new Group();
        group.setId(1L);

        UserGroup userGroup = new UserGroup();
        userGroup.setUser(user);
        userGroup.setGroup(group);

        List<UserGroup> userGroups = Collections.singletonList(userGroup);
        
        when(userGroupRepository.findUserGroupsByGroupId(anyLong())).thenReturn(userGroups);

        List<UserSummaryDto> result = userGroupService.getMembersForGivenGroup(1L);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    void testGetMembersForGivenGroupNotFound() {
        when(userGroupRepository.findUserGroupsByGroupId(anyLong())).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> {
            userGroupService.getMembersForGivenGroup(1L);
        });
    }
}