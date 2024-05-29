package edu.bu.met673.usermanagement.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Errors implements ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,ErrorType.FUNCTIONAL,"com.quicktoppup.account.mgnt.user.notfound","No User have been found for ginven userId"),
    USER_CONTACT_NOT_FOUND(HttpStatus.NOT_FOUND,ErrorType.FUNCTIONAL,"com.quicktoppup.account.mgnt.user.contact.notfound","No UserContact have been found for ginven id"),
    USER_CONTACT_ALREADY_EXIST(HttpStatus.BAD_REQUEST,ErrorType.FUNCTIONAL,"com.quicktoppup.account.mgnt.user.contact.already.exist","Contact already exist for given user");

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
}
