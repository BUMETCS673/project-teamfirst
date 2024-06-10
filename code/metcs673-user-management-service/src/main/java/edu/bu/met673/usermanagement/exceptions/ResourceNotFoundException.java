/**
 * 
 */
package edu.bu.met673.usermanagement.exceptions;

/**
 * @author ajordany
 */
public class ResourceNotFoundException extends BaseException {
	public static final String ERROR_MESSAGE = "The resource you're requesting for is not found";
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(ErrorCode error) {
		super(error, ERROR_MESSAGE);
	}
}
