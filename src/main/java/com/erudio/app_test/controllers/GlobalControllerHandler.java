package com.erudio.app_test.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgument(IllegalArgumentException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
