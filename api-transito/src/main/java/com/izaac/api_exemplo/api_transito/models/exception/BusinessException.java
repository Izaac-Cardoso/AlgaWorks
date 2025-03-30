package com.izaac.api_exemplo.api_transito.models.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
