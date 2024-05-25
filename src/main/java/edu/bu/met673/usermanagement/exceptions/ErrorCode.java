package edu.bu.met673.usermanagement.exceptions;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    HttpStatus getHttpStatus();
    String getCode();
    String getMessage();

    ErrorType getErrorType();
}
