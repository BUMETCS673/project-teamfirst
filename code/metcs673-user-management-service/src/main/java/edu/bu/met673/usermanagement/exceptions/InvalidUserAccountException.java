/**
 * 
 */
package edu.bu.met673.usermanagement.exceptions;

/**
 * InvalidUserAccountException - a custom Exception thrown when an invalid user account is encountered.
 * 
 * Signals a failure to retrieve a valid user profile from the Auth0 service.
 * This exception is thrown when the @auth0Client.getUserProfile method returns `null`,
 * indicating an issue with the user's authentication token or a problem with the Auth0 service itself.
 * 
 * @author ajordany
 */
public class InvalidUserAccountException extends BaseException {
	public static final String ERROR_MESSAGE = "Invalid user account. Failed to retrieve the account from Auth0";
	private static final long serialVersionUID = 1L;
	
	public InvalidUserAccountException(ErrorCode error) {
		super(error,ERROR_MESSAGE);
	}
}
