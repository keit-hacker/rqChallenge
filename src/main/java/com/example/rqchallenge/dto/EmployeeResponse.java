package com.example.rqchallenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeResponse {
    private String status;
    private Object data;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeDTO> getEmployees() {
        if (data instanceof List) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(data, mapper.getTypeFactory().constructCollectionType(List.class, EmployeeDTO.class));
        }
        return null;
    }

    public EmployeeDTO getEmployee() {
        if (data instanceof EmployeeDTO) {
            return (EmployeeDTO) data;
        } else if (data != null){
            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(data, EmployeeDTO.class);
        }
        return null;
    }
}