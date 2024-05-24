/**
 * 
 */
package edu.bu.met673.usermanagement.service;

import edu.bu.met673.usermanagement.api.model.UserDto;
import edu.bu.met673.usermanagement.exceptions.ServiceException;

/**
 * Interface for managing users
 * 
 */
public interface UserService {
	
	/**
	 * register a new user
	 * 
	 * @param userDto
	 * @return
	 * @throws ServiceException
	 */
	public UserDto registerUser(UserDto userDto) throws ServiceException;

	/**
	 * Update information about a given user
	 * 
	 * @param userId
	 * @param userDto
	 * @return
	 * @throws ServiceException
	 */
	public UserDto updateUser(Long userId, UserDto userDto) throws ServiceException;
	
}
