package com.example.rqchallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateEmployeeResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private EmployeeDTO data;

    @JsonProperty("message")
    private String message;

    // Default constructor
    public CreateEmployeeResponse() {}

    // Parameterized constructor
    public CreateEmployeeResponse(String status, EmployeeDTO data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EmployeeDTO getData() {
        return data;
    }

    public void setData(EmployeeDTO data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
