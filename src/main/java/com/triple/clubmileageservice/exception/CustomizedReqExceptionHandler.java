package com.triple.clubmileageservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class CustomizedReqExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> reqExceptions(MethodArgumentNotValidException e, HttpServletRequest request) {
        ExceptionRes exceptionRes = new ExceptionRes(new Date(),
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionRes, HttpStatus.BAD_REQUEST);
    }
}
