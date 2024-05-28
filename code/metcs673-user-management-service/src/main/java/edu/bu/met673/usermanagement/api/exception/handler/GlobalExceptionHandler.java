package edu.bu.met673.usermanagement.api.exception.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import edu.bu.met673.usermanagement.exceptions.ErrorCode;
import edu.bu.met673.usermanagement.exceptions.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.application.name}")
    private String appName;

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<RestProblem> handleServiceException(final ServiceException serviceException, final HttpServletRequest httpServletRequest){
        ErrorCode errorCode = serviceException.getErrorCode();
        RestProblem restProblem = new RestProblem(errorCode.getHttpStatus());
        RestError error = new RestError(httpServletRequest.getRequestURI(),errorCode.getMessage(),serviceException,errorCode.getErrorType(),appName);
        restProblem.getErrors().add(error);
        return ResponseEntity.status(errorCode.getHttpStatus()).body(restProblem);
    }
}
