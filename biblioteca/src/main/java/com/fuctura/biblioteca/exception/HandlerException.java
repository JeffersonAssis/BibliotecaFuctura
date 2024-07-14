package com.fuctura.biblioteca.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice
public class HandlerException {
  
  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<?> objectNotFunExException(ObjectNotFoundException err, HttpServletRequest request){
    ExceptionEntity ee = new ExceptionEntity(LocalDateTime.now(), err.getMessage(), HttpStatus.NOT_FOUND.value(), request.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ee);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<?> illegalArgumentException(IllegalArgumentException err, HttpServletRequest request){
        ExceptionEntity ee = new ExceptionEntity(LocalDateTime.now(), err.getMessage(), HttpStatus.BAD_REQUEST.value(), request.getRequestURI());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ee);
  }

}
