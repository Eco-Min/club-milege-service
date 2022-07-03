package com.triple.clubmileageservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizedResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserException.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest req) {
        ExceptionRes exceptionRes = new ExceptionRes(new Date(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(exceptionRes, HttpStatus.BAD_REQUEST);
    }
}
