package com.example.rqchallenge.handler;

import com.example.rqchallenge.exception.EmployeeNotFoundException;
import com.example.rqchallenge.exception.EmployeeCreationException;
import com.example.rqchallenge.exception.EmployeeDeletionException;
import com.example.rqchallenge.exception.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleEmployeeNotFoundException() {
        EmployeeNotFoundException ex = new EmployeeNotFoundException("Employee not found");
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletWebRequest webRequest = new ServletWebRequest(request);

        ResponseEntity<ApiException> responseEntity = exceptionHandler.handleEmployeeNotFoundException(ex, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Employee not found", responseEntity.getBody().getMessage());
    }

    @Test
    void handleEmployeeCreationException() {
        EmployeeCreationException ex = new EmployeeCreationException("Failed to create employee");
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletWebRequest webRequest = new ServletWebRequest(request);

        ResponseEntity<ApiException> responseEntity = exceptionHandler.handleEmployeeCreationException(ex, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Failed to create employee", responseEntity.getBody().getMessage());
    }

    @Test
    void handleEmployeeDeletionException() {
        EmployeeDeletionException ex = new EmployeeDeletionException("Failed to delete employee");
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletWebRequest webRequest = new ServletWebRequest(request);

        ResponseEntity<ApiException> responseEntity = exceptionHandler.handleEmployeeDeletionException(ex, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Failed to delete employee", responseEntity.getBody().getMessage());
    }

    @Test
    void handleGlobalException() {
        Exception ex = new Exception("Unexpected error");
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletWebRequest webRequest = new ServletWebRequest(request);

        ResponseEntity<ApiException> responseEntity = exceptionHandler.handleGlobalException(ex, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("An unexpected error occurred", responseEntity.getBody().getMessage());
    }
}