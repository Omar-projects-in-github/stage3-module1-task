package com.mjc.school.service.exception;

public class IDNotFoundException extends RuntimeException {
    public IDNotFoundException(String message) {
        super(message);
    }
}
