package com.naisilva.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, ServletRequest request){

        StandardError error = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
}
