package edu.bu.met673.usermanagement.mapper;

import edu.bu.met673.usermanagement.api.model.UserDto;
import edu.bu.met673.usermanagement.api.model.UserRegistrationRequest;
import edu.bu.met673.usermanagement.api.model.UserSummaryDto;
import edu.bu.met673.usermanagement.entities.User;


public interface UserMapper {
	
    User toEntity(UserRegistrationRequest dto);
	UserDto toDto(User entity);
	
	public static UserSummaryDto toUserSummaryDto(User user) {
        if (user == null) return null;
        UserSummaryDto dto = new UserSummaryDto();
        dto.setId(user.getId());
        dto.setIdentityProviderId(user.getIdentityProviderId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstname());
        dto.setLastName(user.getLastname());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
