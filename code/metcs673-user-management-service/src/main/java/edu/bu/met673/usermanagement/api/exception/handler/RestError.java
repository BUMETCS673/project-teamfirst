package edu.bu.met673.usermanagement.api.exception.handler;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import edu.bu.met673.usermanagement.exceptions.ErrorType;
import edu.bu.met673.usermanagement.exceptions.ExceptionHelper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestError {

    private String url;
    private String message;
    private String exception;
    private String stackTrace;

    private String systemName;
    private ErrorType errorType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    public RestError(String url, String message, Throwable cause,  ErrorType errorType,  String systemName){
        this.url = url;
        this.message = message;
        this.stackTrace = ExceptionHelper.getStackTrace(cause);
        this.exception =  ExceptionHelper.getException(cause);
        this.dateTime = LocalDateTime.now();
        this.systemName = systemName;
        this.errorType = errorType;
    }


}
