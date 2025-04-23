package com.izaac.api_exemplo.api_transito.exceptionHandler;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
