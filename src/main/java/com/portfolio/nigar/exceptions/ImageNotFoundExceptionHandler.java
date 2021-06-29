package com.portfolio.nigar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ImageNotFoundExceptionHandler {

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(ImageNotFoundException im){
        return new ResponseEntity<>("Image not found", HttpStatus.NOT_FOUND);

    }

}
