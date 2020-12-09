package com.simon.october.exception;

public class CannotInitializeConstructorException extends RuntimeException {
    public CannotInitializeConstructorException(String msg) {
        super(msg);
    }
}
