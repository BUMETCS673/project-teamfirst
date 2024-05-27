package edu.bu.met673.usermanagement.exceptions;

public class ServiceException extends RuntimeException {

    private final transient ErrorCode errorCode;

    public ServiceException(ErrorCode errorCode) {
        this(errorCode,errorCode.getMessage());
    }

    public ServiceException(ErrorCode errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceException(ErrorCode errorCode, Throwable cause){
        this(errorCode,errorCode.getMessage(),cause);
    }

    public ServiceException(ErrorCode errorCode,String message, Throwable cause) {
        super(message,cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode(){
        return this.errorCode;
    }
}
