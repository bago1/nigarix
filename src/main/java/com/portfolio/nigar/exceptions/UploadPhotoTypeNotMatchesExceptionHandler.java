package com.portfolio.nigar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UploadPhotoTypeNotMatchesExceptionHandler {

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<Object> handlephotoTypeNotMatches(UploadPhotoTypeNotMatchesException ue){
        return new ResponseEntity<>("Check Photo Type to Upload", HttpStatus.NOT_ACCEPTABLE);

    }

}
