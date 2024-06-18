package edu.bu.met673.usermanagement.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.bu.met673.usermanagement.api.model.PageData;
import edu.bu.met673.usermanagement.api.model.UserDto;
import edu.bu.met673.usermanagement.api.model.UserRegistrationRequest;
import edu.bu.met673.usermanagement.auth0.client.Auth0Client;
import edu.bu.met673.usermanagement.auth0.client.Auth0User;
import edu.bu.met673.usermanagement.entities.User;
import edu.bu.met673.usermanagement.exceptions.Errors;
import edu.bu.met673.usermanagement.exceptions.InvalidUserAccountException;
import edu.bu.met673.usermanagement.exceptions.ResourceNotFoundException;
import edu.bu.met673.usermanagement.exceptions.ServiceException;
import edu.bu.met673.usermanagement.exceptions.UserProfileAlreadyExistsException;
import edu.bu.met673.usermanagement.mapper.UserMapper;
import edu.bu.met673.usermanagement.repositories.UserRepository;
import edu.bu.met673.usermanagement.service.UserService;
import edu.bu.met673.usermanagement.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;

/**
 *   UsersServiceImpl - Implementation class of the @UserService
 *   
 *   @ajordany
 */
@Transactional
@Service
@Slf4j
public class UsersServiceImpl implements UserService {

	private UserRepository userRepository;
	private Auth0Client auth0Client;
	private UserMapper mapper;
    
    public UsersServiceImpl(UserRepository userRepository,
    		Auth0Client auth0Client,UserMapper mapper) {
    	this.userRepository = userRepository;
    	this.auth0Client = auth0Client;
    	this.mapper = mapper;
    }
    
    private boolean isUserProfileAlreadyExist(String username, String email
    		,String identityProviderId ) {
    	return   userRepository.findByIdentityProviderId(identityProviderId).isPresent()
    			|| Objects.nonNull(userRepository.findByUsername(username))
    			|| Objects.nonNull(userRepository.findByEmail(email));
    }
    
    private String getIdentityProviderId(Auth0User auth0User) {
    	if(Objects.nonNull(auth0User)) {
    		log.info("User : {}", auth0User);
    		return auth0User.getSub()/*.split("|")[1]*/;
    	}
    	return "";
    }
    
    /** 
     * @see UserService#register 
     * */
    @Override()
	public UserDto register(UserRegistrationRequest registrationRequest) {
		try {
			
	    	Auth0User auth0User = auth0Client.getUserProfile(TokenUtils.getToken());
			if (Objects.isNull(auth0User)) {
				log.error("{}", Errors.INVALID_USER_ACCOUNT);
				throw new InvalidUserAccountException(Errors.INVALID_USER_ACCOUNT);
			}
	
			String identityProviderId = this.getIdentityProviderId(auth0User);
			if(isUserProfileAlreadyExist(registrationRequest.getUsername(), registrationRequest.getEmail(), identityProviderId)) {
				log.error("{}", Errors.USER_PROFILE_ALREADY_EXIST);
				throw new UserProfileAlreadyExistsException(Errors.USER_PROFILE_ALREADY_EXIST);
			}
	
			User user = mapper.toEntity(registrationRequest);
			user.setIdentityProviderId(identityProviderId);
			UserDto userDto = this.mapper.toDto(userRepository.save(user));
			userDto.setPermissions(TokenUtils.getClaims());
			if(log.isDebugEnabled()) {
				log.info("User with email:{}, username:{}, Identity Id:{} registered successfully.", 
						registrationRequest.getUsername(), registrationRequest.getEmail(),user.getIdentityProviderId());
			}
			return userDto;
			
		}catch(InvalidUserAccountException | UserProfileAlreadyExistsException ex) {
			throw ex;
		}catch(Exception ex) {
			ex.printStackTrace();
			log.error("{}", Errors.INTERNAL_ERROR);
			throw ServiceException.of(Errors.INTERNAL_ERROR);
		}
	}

	@Override
	public UserDto getMyProfile() throws ServiceException, InvalidUserAccountException {
		try {
			String  userIdentityProviderId = this.getIdentityProviderId(
					auth0Client.getUserProfile(TokenUtils.getToken()));
			
			Optional<User> user = this.userRepository.findByIdentityProviderId(userIdentityProviderId);
			if(user.isEmpty()) {
				log.error("{}", Errors.INVALID_USER_ACCOUNT);
				throw new InvalidUserAccountException(Errors.INVALID_USER_ACCOUNT);
			}
			
			UserDto userDto = this.mapper.toDto(user.get());
			userDto.setPermissions(TokenUtils.getClaims());
			return this.mapper.toDto(user.get());
		
		}catch(InvalidUserAccountException ex) {
			throw ex;
		}catch(Exception ex) {
			ex.printStackTrace();
			log.error("{}", Errors.INTERNAL_ERROR);
			throw ServiceException.of(Errors.INTERNAL_ERROR);
		}
	}

	@Override
	public UserDto getUserProfile(Long userId) throws ServiceException, ResourceNotFoundException {
		try {
			Optional<User> user = this.userRepository.findById(userId);
			if(user.isEmpty()) {
				log.error("{}", Errors.RESOURCE_NOT_FOUND);
				throw new ResourceNotFoundException(Errors.RESOURCE_NOT_FOUND);
			}
			
			return this.mapper.toDto(user.get());
		
		}catch(ResourceNotFoundException ex) {
			throw ex;
		}catch(Exception ex) {
			ex.printStackTrace();
			log.error("{}", Errors.INTERNAL_ERROR);
			throw ServiceException.of(Errors.INTERNAL_ERROR);
		}
	}

	@Override
	public PageData<UserDto> getAllUserProfiles(String filter, Pageable pageable) throws ServiceException {
		try {
			Page<User> data = this.userRepository.findUsers(filter, pageable);
			return new PageData<>(data.getContent().stream().map(this.mapper::toDto).toList(),
					pageable, data.getTotalElements());
			
		}catch(Exception ex) {
			ex.printStackTrace();
			log.error("{}", Errors.INTERNAL_ERROR);
			throw ServiceException.of(Errors.INTERNAL_ERROR);
		}
	}

	@Override
	public UserDto updateUserProfile(Long userId, UserRegistrationRequest userDto) throws ServiceException, InvalidUserAccountException {
		try {
			String  userIdentityProviderId = this.getIdentityProviderId(
					auth0Client.getUserProfile(TokenUtils.getToken()));
			
			Optional<User> user = this.userRepository.findByIdentityProviderId(userIdentityProviderId);
			if(user.isEmpty()) user = this.userRepository.findById(userId); 
			
			
			if(user.isEmpty()) {
				log.error("{}", Errors.INVALID_USER_ACCOUNT);
				throw new InvalidUserAccountException(Errors.INVALID_USER_ACCOUNT);
			}
			
			User userToUpdate = mapper.toEntity(userDto);
			User existinguser = user.get();
			userToUpdate.setId(existinguser.getId());
			userToUpdate.setCreatedAt(existinguser.getCreatedAt());
			userToUpdate.setUpdatedAt(existinguser.getUpdatedAt());
	
			return this.mapper.toDto(userRepository.save(userToUpdate));
		
		}catch(InvalidUserAccountException ex) {
			throw ex;
		}catch(Exception ex) {
			ex.printStackTrace();
			log.error("{}", Errors.INTERNAL_ERROR);
			throw ServiceException.of(Errors.INTERNAL_ERROR);
		}
	}
}
