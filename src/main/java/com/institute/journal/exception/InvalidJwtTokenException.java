package com.institute.journal.exception;

public class InvalidJwtTokenException extends RuntimeException{
    public InvalidJwtTokenException() {
    }

    public InvalidJwtTokenException(String message) {
        super(message);
    }
}
