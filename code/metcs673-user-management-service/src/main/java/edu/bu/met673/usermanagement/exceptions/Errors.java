package edu.bu.met673.usermanagement.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Errors implements ErrorCode {
	INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ErrorType.SYSTEM,"edu.bu.met673.usermanagement.service", "Unable to proceed with your request."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND,ErrorType.FUNCTIONAL,"edu.bu.met673.usermanagement.service.user-profiles","No resource have been found for ginven ID"),
	INVALID_USER_ACCOUNT(HttpStatus.NOT_FOUND,ErrorType.FUNCTIONAL,"edu.bu.met673.usermanagement.service.user-prfiles","Invalid user account. Account must be created first in Auth0"),
	USER_PROFILE_ALREADY_EXIST(HttpStatus.NOT_FOUND,ErrorType.FUNCTIONAL,"edu.bu.met673.usermanagement.service.user-profiles","User profile already exist.");

    private HttpStatus httpStatus;
    private ErrorType errorType;
    private String code;
    private String message;

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public ErrorType getErrorType() {
        return this.errorType;
    }
    
    public ErrorResponse toErrorResponse() {
        return new ErrorResponse(this.httpStatus, this.errorType, this.code, this.message);
    }
}
