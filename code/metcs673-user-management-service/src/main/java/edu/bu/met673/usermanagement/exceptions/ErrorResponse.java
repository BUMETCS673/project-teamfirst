/**
 * 
 */
package edu.bu.met673.usermanagement.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * 
 */
@Data
public class ErrorResponse {
    private final HttpStatus httpStatus;
    private final ErrorType errorType;
    private final String code;
    private final String message;

    public ErrorResponse(HttpStatus httpStatus, ErrorType errorType, String code, String message) {
        this.httpStatus = httpStatus;
        this.errorType = errorType;
        this.code = code;
        this.message = message;
    }
}
