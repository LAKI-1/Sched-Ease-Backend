package com.sched_ease.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

// Global exception handler for handling various exceptions across the app
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle IllegalArgumentException (Bad Request)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Bad Request", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Handle specific custom exception (e.g., MemberNotFoundException)
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleMemberNotFound(MemberNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Members Not Found", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Handle generic exception (Internal Server Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Internal Server Error", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
