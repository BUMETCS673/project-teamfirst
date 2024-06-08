package edu.bu.met673.usermanagement.mapper;

import edu.bu.met673.usermanagement.api.model.UserDto;
import edu.bu.met673.usermanagement.api.model.UserRegistrationRequest;
import edu.bu.met673.usermanagement.entities.User;


public interface UserMapper {
	
    User toEntity(UserRegistrationRequest dto);
	UserDto toDto(User entity);
}
