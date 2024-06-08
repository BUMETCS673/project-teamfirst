package edu.bu.met673.usermanagement.exceptions;

public abstract class BaseException extends RuntimeException {

    protected static final long serialVersionUID = 1L;
	protected final transient ErrorCode errorCode;

    protected BaseException(ErrorCode errorCode) {
        this(errorCode,errorCode.getMessage());
    }

     protected BaseException(ErrorCode errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }

    protected BaseException(ErrorCode errorCode, Throwable cause){
        this(errorCode,errorCode.getMessage(),cause);
    }

    protected BaseException(ErrorCode errorCode,String message, Throwable cause) {
        super(message,cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode(){
        return this.errorCode;
    }
}
