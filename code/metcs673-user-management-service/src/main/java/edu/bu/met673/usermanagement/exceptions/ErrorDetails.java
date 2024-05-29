/**
 * 
 */
package edu.bu.met673.usermanagement.exceptions;

import lombok.Data;

/**
 * @author ajord
 *
 */
@Data
public class ErrorDetails {
	
	private String message;
	private String key;
	private String value;
	private String type;

}
