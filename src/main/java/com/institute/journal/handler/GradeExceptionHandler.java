package com.institute.journal.handler;

import com.institute.journal.exception.GradeDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GradeExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<GradeDoesNotExistException> catchGradeDoesNotExistException(GradeDoesNotExistException e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
