package com.hepsibirarada.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> exceptionResponseEntity(CustomApplicationException customApplicationException){
        ExceptionResponse exceptionResponse=new ExceptionResponse();
        exceptionResponse.setMessage(customApplicationException.getMessage());
        exceptionResponse.setStatus(HttpStatus.NOT_FOUND.value());
        exceptionResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> exceptionResponseResponseEntity(RuntimeException exc){
        ExceptionResponse exceptionResponse=new ExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage("Bad request!  "+exc.getMessage());
        exceptionResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

}
