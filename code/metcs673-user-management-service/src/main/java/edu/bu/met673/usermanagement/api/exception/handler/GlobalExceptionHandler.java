package edu.bu.met673.usermanagement.api.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import edu.bu.met673.usermanagement.exceptions.BaseException;
import edu.bu.met673.usermanagement.exceptions.ErrorCode;
import edu.bu.met673.usermanagement.exceptions.ErrorResponse;
import edu.bu.met673.usermanagement.exceptions.Errors;
import edu.bu.met673.usermanagement.exceptions.InvalidUserAccountException;
import edu.bu.met673.usermanagement.exceptions.ResourceNotFoundException;
import edu.bu.met673.usermanagement.exceptions.ServiceException;
import edu.bu.met673.usermanagement.exceptions.UserProfileAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
    	ServiceException.class, 
    	InvalidUserAccountException.class, 
    	ResourceNotFoundException.class,
    	UserProfileAlreadyExistsException.class})
    public ResponseEntity<ErrorResponse> handleCustomException(final BaseException serviceException
    		, final HttpServletRequest httpServletRequest){
    	ErrorCode errorCode = serviceException.getErrorCode();
    	log.info("handleServiceException : {}");
    	return ResponseEntity.status(errorCode.getHttpStatus()).body(errorCode.toErrorResponse());
    }
    
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorCode> handleServiceException(final Exception ex
    		, final HttpServletRequest httpServletRequest){
    	ErrorCode errorCode = Errors.INTERNAL_ERROR;
    	return ResponseEntity.status(errorCode.getHttpStatus()).body(errorCode);
    }
    
}
