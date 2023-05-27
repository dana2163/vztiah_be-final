package com.institute.journal.exception;

public class GradeDoesNotExistException extends RuntimeException{
    public GradeDoesNotExistException() {
    }

    public GradeDoesNotExistException(String message) {
        super(message);
    }
}
