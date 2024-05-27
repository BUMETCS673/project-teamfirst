package edu.bu.met673.usermanagement.mapper;

import javax.annotation.processing.Generated;

import org.springframework.stereotype.Component;

import edu.bu.met673.usermanagement.api.model.RoleDto;
import edu.bu.met673.usermanagement.api.model.UserDto;
import edu.bu.met673.usermanagement.entities.User;
import edu.bu.met673.usermanagement.entities.UserRole;

//@Generated(
//        value = "org.mapstruct.ap.MappingProcessor",
//        date = "2024-05-19T18:14:54-0400",
//        comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 19.0.2 (Oracle Corporation)"
//)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setFirstname( dto.getFirstName() );
        user.setLastname( dto.getLastName() );
        user.setId( dto.getId() );
        user.setCreatedAt( dto.getCreatedAt() );
        user.setUpdatedAt( dto.getUpdatedAt() );
        user.setIdentityProviderId( dto.getIdentityProviderId() );
        user.setPhone( dto.getPhone() );
        user.setEmail( dto.getEmail() );
        user.setUsername( dto.getUsername() );
        user.setCity( dto.getCity() );
        user.setState( dto.getState() );
        user.setPostalCode( dto.getPostalCode() );
        user.setCountry( dto.getCountry() );
        user.setRole( roleDtoToUserRole( dto.getRole() ) );

        return user;
    }

    @Override
    public UserDto toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setFirstName( entity.getFirstname() );
        userDto.setLastName( entity.getLastname() );
        if ( entity.getId() != null ) {
            userDto.setId( entity.getId() );
        }
        userDto.setIdentityProviderId( entity.getIdentityProviderId() );
        userDto.setPhone( entity.getPhone() );
        userDto.setEmail( entity.getEmail() );
        userDto.setUsername( entity.getUsername() );
        userDto.setCity( entity.getCity() );
        userDto.setState( entity.getState() );
        userDto.setPostalCode( entity.getPostalCode() );
        userDto.setCountry( entity.getCountry() );
        userDto.setRole( userRoleToRoleDto( entity.getRole() ) );
        userDto.setCreatedAt( entity.getCreatedAt() );
        userDto.setUpdatedAt( entity.getUpdatedAt() );

        return userDto;
    }

    protected UserRole roleDtoToUserRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        UserRole userRole = new UserRole();

        userRole.setId( roleDto.getId() );
        userRole.setName( roleDto.getName() );
        userRole.setCreatedAt( roleDto.getCreatedAt() );
        userRole.setUpdatedAt( roleDto.getUpdatedAt() );

        return userRole;
    }

    protected RoleDto userRoleToRoleDto(UserRole userRole) {
        if ( userRole == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        if ( userRole.getId() != null ) {
            roleDto.setId( userRole.getId() );
        }
        roleDto.setName( userRole.getName() );
        roleDto.setCreatedAt( userRole.getCreatedAt() );
        roleDto.setUpdatedAt( userRole.getUpdatedAt() );

        return roleDto;
    }
}
