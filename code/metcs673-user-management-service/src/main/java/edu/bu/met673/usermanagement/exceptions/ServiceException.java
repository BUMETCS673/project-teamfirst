package edu.bu.met673.usermanagement.exceptions;

public class ServiceException extends BaseException {

    private static final long serialVersionUID = 1L;

	public ServiceException(ErrorCode errorCode) {
        super(errorCode,errorCode.getMessage());
    }
	
	public static ServiceException  of(ErrorCode errorCode) {
		return new ServiceException(errorCode);
	}

}
