package com.mjc.school.service.exception;

public class ArgumentValidException extends RuntimeException {
    public ArgumentValidException(String message) {
        super(message);
    }
}
