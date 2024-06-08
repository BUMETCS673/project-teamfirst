/**
 * 
 */
package edu.bu.met673.usermanagement.service;

import org.springframework.data.domain.Pageable;

import edu.bu.met673.usermanagement.api.model.PageData;
import edu.bu.met673.usermanagement.api.model.UserDto;
import edu.bu.met673.usermanagement.api.model.UserRegistrationRequest;
import edu.bu.met673.usermanagement.exceptions.InvalidUserAccountException;
import edu.bu.met673.usermanagement.exceptions.ServiceException;
import edu.bu.met673.usermanagement.exceptions.UserProfileAlreadyExistsException;

/**
 * The UserService interface provides methods for user management including registration,
 * retrieval, updating profiles, and listing all users with filtering and pagination options.
 * 
 * @author ajordany
 */
public interface UserService {

    /**
     * Registers a new user based on the provided registration request data.
     * 
     * @param registrationRequest the request object containing user registration details
     * @return the DTO containing the registered user's information
     * @throws ServiceException if a general service error occurs
     * @throws InvalidUserAccountException if the user account details are invalid
     * @throws UserProfileAlreadyExistsException if a user profile already exists with the same details
     */
    UserDto register(UserRegistrationRequest registrationRequest) 
            throws ServiceException, InvalidUserAccountException, UserProfileAlreadyExistsException;

    /**
     * Retrieves the profile information of the currently authenticated user.
     * 
     * @return the DTO containing the profile information of the current user
     * @throws ServiceException if a general service error occurs
     * @throws InvalidUserAccountException if the user account details are invalid
     */
    UserDto getMyProfile() throws ServiceException, InvalidUserAccountException;

    /**
     * Retrieves the profile information of a user by their unique user ID.
     * 
     * @param userId the unique identifier of the user whose profile is to be retrieved
     * @return the DTO containing the profile information of the specified user
     * @throws ServiceException if a general service error occurs
     */
    UserDto getUserProfile(Long userId) throws ServiceException;

    /**
     * Retrieves a paginated list of all user profiles, optionally filtered by a search term.
     * 
     * @param filter a string to filter the user profiles, can be null or empty for no filtering
     * @param pageable the pagination information including page number and size
     * @return a paginated list of user profile DTOs
     * @throws ServiceException if a general service error occurs
     */
    PageData<UserDto> getAllUserProfiles(String filter, Pageable pageable) throws ServiceException;

    /**
     * Updates the profile information of a user identified by their unique user ID.
     * 
     * @param userId the unique identifier of the user whose profile is to be updated
     * @param userDto the request object containing the updated user profile details
     * @return the DTO containing the updated profile information of the user
     * @throws ServiceException if a general service error occurs
     * @throws InvalidUserAccountException if the user account details are invalid
     */
    UserDto updateUserProfile(Long userId, UserRegistrationRequest userDto) 
            throws ServiceException, InvalidUserAccountException;

}
