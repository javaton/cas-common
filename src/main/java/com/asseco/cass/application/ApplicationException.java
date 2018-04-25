package com.asseco.cass.application;

import javax.persistence.PersistenceException;

public abstract class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = -4842135735827206771L;
    private ApplicationErrorCode errorCode;

    public ApplicationException(ApplicationErrorCode errorCode) {
        super(errorCode.toString());
        this.errorCode = errorCode;
    }

    public ApplicationException(ApplicationErrorCode errorCode, String fildError) {
        super(errorCode + " " + fildError);
        this.errorCode = errorCode;
    }

    protected ApplicationException(PersistenceException e) {}

    public ApplicationErrorCode getErrorCode() {
        return errorCode;
    }
}