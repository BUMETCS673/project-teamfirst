/**
 * 
 */
package edu.bu.met673.usermanagement.mapper;

/**
 * 
 */
import edu.bu.met673.usermanagement.api.model.GroupDto;
import edu.bu.met673.usermanagement.api.model.UserGroupDto;
import edu.bu.met673.usermanagement.entities.Group;
import edu.bu.met673.usermanagement.entities.UserGroup;

public final class UserGroupMapper {
	
	private UserGroupMapper() {}

    // Convert Group entity to GroupDto
    public static GroupDto toGroupDto(Group group) {
        if (group == null) return null;
        GroupDto dto = new GroupDto();
        dto.setGroupId(group.getId());
        dto.setName(group.getName());
        dto.setDescription(group.getDescription());
        dto.setCreatedBy(group.getCreatedBy());
        return dto;
    }

    // Convert GroupDto to Group entity
    public static Group toGroupEntity(GroupDto dto) {
        if (dto == null) return null;
        Group group = new Group();
        group.setName(dto.getName());
        group.setDescription(dto.getDescription());
        group.setCreatedBy(dto.getCreatedBy());
        return group;
    }

    // Convert UserGroup entity to UserGroupDto
    public static UserGroupDto toUserGroupDto(UserGroup userGroup) {
        if (userGroup == null) return null;
        UserGroupDto dto = new UserGroupDto();
        dto.setJoinedAt(userGroup.getJoinedAt());
        dto.setGorupRole(userGroup.getGorupRole());
        dto.setUser(UserMapper.toUserSummaryDto(userGroup.getUser()));
        dto.setGroup(UserGroupMapper.toGroupDto(userGroup.getGroup()));
        return dto;
    }

    // Convert UserGroupDto to UserGroup entity
    public static UserGroup toUserGroupEntity(UserGroupDto dto) {
        if (dto == null) return null;
        UserGroup userGroup = new UserGroup();
        userGroup.setJoinedAt(dto.getJoinedAt());
        userGroup.setGorupRole(dto.getGorupRole());
        return userGroup;
    }
}
