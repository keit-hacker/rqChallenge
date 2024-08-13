package com.example.rqchallenge.handler;

import com.example.rqchallenge.exception.ApiException;
import com.example.rqchallenge.exception.EmployeeNotFoundException;
import com.example.rqchallenge.exception.EmployeeCreationException;
import com.example.rqchallenge.exception.EmployeeDeletionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ApiException> handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest request) {
        logger.warn("Employee not found: {}", ex.getMessage());
        ApiException apiException = new ApiException(ex.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeCreationException.class)
    public ResponseEntity<ApiException> handleEmployeeCreationException(EmployeeCreationException ex, WebRequest request) {
        logger.error("Employee creation failed: {}", ex.getMessage(), ex);
        ApiException apiException = new ApiException(ex.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeDeletionException.class)
    public ResponseEntity<ApiException> handleEmployeeDeletionException(EmployeeDeletionException ex, WebRequest request) {
        logger.error("Employee deletion failed: {}", ex.getMessage(), ex);
        ApiException apiException = new ApiException(ex.getMessage(), LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiException> handleGlobalException(Exception ex, WebRequest request) {
        logger.error("Unexpected error occurred: {}", ex.getMessage(), ex);
        ApiException apiException = new ApiException("An unexpected error occurred", LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}