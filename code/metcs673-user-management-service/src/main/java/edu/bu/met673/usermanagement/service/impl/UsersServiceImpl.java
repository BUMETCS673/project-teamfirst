package edu.bu.met673.usermanagement.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.bu.met673.usermanagement.api.model.UserDto;
import edu.bu.met673.usermanagement.entities.User;
import edu.bu.met673.usermanagement.exceptions.Errors;
import edu.bu.met673.usermanagement.exceptions.ServiceException;
import edu.bu.met673.usermanagement.mapper.UserMapper;
import edu.bu.met673.usermanagement.repositories.UserRepository;
import edu.bu.met673.usermanagement.service.UserService;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UsersServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
   
    @Autowired
	private UserMapper mapper;

	public UserDto registerUser(UserDto userDto){
		User user = null;
		Optional<User> userOptional = this.userRepository.findByIdentityProviderId(userDto.getIdentityProviderId());
	 if(userOptional.isEmpty()){
		 log.info("===== Register User ====");
		 user = mapper.toEntity(userDto);
	 }else{
		 log.info("===== User Already Exist ====");
		 user = this.updateUser(userDto,userOptional.get());
	 }

		return this.mapper.toDto(user);
	}


	public UserDto updateUser(Long userId, UserDto userDto){
		Optional<User> userOptional = this.userRepository.findById(userId);
		if(!userOptional.isEmpty()){
			return this.mapper.toDto(this.updateUser(userDto,userOptional.get()));
		}else{
			throw new ServiceException(Errors.USER_NOT_FOUND);
		}
	}

	private User updateUser(UserDto userDto, User user){
		user.setFirstname(userDto.getFirstName());
		user.setLastname(userDto.getLastName());
		user.setUpdatedAt(LocalDateTime.now());
		return this.userRepository.save(user);
	}


	@Override
	public UserDto getMyProfile(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
