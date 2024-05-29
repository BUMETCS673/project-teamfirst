package edu.bu.met673.usermanagement.mapper;

import org.springframework.stereotype.Component;

import edu.bu.met673.usermanagement.api.model.UserDto;
import edu.bu.met673.usermanagement.entities.User;

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
       // user.setId( dto.getId() );
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
           // userDto.setId( entity.getId() );
        }
        userDto.setIdentityProviderId( entity.getIdentityProviderId() );
        userDto.setPhone( entity.getPhone() );
        userDto.setEmail( entity.getEmail() );
        userDto.setUsername( entity.getUsername() );
        userDto.setCity( entity.getCity() );
        userDto.setState( entity.getState() );
        userDto.setPostalCode( entity.getPostalCode() );
        userDto.setCountry( entity.getCountry() );
        userDto.setCreatedAt( entity.getCreatedAt() );
        userDto.setUpdatedAt( entity.getUpdatedAt() );

        return userDto;
    }

}
