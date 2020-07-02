package com.demo.mobileappws.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex, WebRequest request) {

        String errorMessageDescription = ex.getLocalizedMessage();
        if(errorMessageDescription==null) errorMessageDescription= ex.toString();

        ErrorMessage errorObj = new ErrorMessage(new Date(), errorMessageDescription);
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NullPointerException.class,UserServiceException.class})
    public final ResponseEntity<ErrorMessage> handleSpecificPointerExceptions(Exception ex, WebRequest request) {

        String errorMessageDescription = ex.getLocalizedMessage();
        if(errorMessageDescription==null) errorMessageDescription= ex.toString();

        ErrorMessage errorObj = new ErrorMessage(new Date(), errorMessageDescription);
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}


