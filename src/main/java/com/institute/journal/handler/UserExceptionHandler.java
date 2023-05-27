package com.institute.journal.handler;

import com.institute.journal.exception.UserAlreadyExistsException;
import com.institute.journal.exception.UserDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserAlreadyExistsException> catchUserAlreadyExistException(UserAlreadyExistsException e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<UserDoesNotExistException> catchUserAlreadyExistException(UserDoesNotExistException e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
