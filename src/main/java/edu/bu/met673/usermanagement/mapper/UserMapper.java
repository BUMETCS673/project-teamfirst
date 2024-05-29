package edu.bu.met673.usermanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.bu.met673.usermanagement.api.model.UserDto;
import edu.bu.met673.usermanagement.entities.User;


//@Mapper(componentModel ="spring")
public interface UserMapper {
	
//	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
//
//    @Mapping(target="firstname", source = "firstName")
//	@Mapping(target="lastname", source = "lastName")
    User toEntity(UserDto dto);

//	@Mapping(target="firstName", source = "firstname")
//	@Mapping(target="lastName", source = "lastname")
	UserDto toDto(User entity);
}
