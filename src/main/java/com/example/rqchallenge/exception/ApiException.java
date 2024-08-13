package com.example.rqchallenge.exception;

import java.time.LocalDateTime;

public class ApiException {
    private final String message;
    private final LocalDateTime timestamp;
    private final int status;

    public ApiException(String message, LocalDateTime timestamp, int status) {
        this.message = message;
        this.timestamp = timestamp;
        this.status = status;
    }

    // Getters

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }
}