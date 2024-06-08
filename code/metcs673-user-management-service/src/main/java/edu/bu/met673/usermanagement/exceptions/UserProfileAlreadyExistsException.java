/**
 * 
 */
package edu.bu.met673.usermanagement.exceptions;

/**
 * UserProfileAlreadyExistsException - Custom Exception thrown when attempting to register a user that already exists.
 * 
 * This exception is thrown when either @userRepository.findByUsername
 * or @userRepository.findByEmail or @userRepository.findByIdentityProviderId returns a non-null value
 * 
 * @author ajordany
 */
public class UserProfileAlreadyExistsException extends BaseException {
	public static final String ERROR_MESSAGE = "Username or email already exists";
	private static final long serialVersionUID = 1L;
	

	public UserProfileAlreadyExistsException(ErrorCode error) {
		super(error, ERROR_MESSAGE);
	}

}
