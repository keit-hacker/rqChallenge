package com.example.rqchallenge.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DTOTest {

    @Test
    void testCreateEmployeeResponse() {
        EmployeeDTO employeeDTO = new EmployeeDTO("1", "John Doe", "50000", "30", "");
        CreateEmployeeResponse response = new CreateEmployeeResponse("success", employeeDTO, "Employee created");

        assertEquals("success", response.getStatus());
        assertEquals(employeeDTO, response.getData());
        assertEquals("Employee created", response.getMessage());
    }

    @Test
    void testEmployeeDTO() {
        EmployeeDTO employee = new EmployeeDTO("1", "John Doe", "50000", "30", "");

        assertEquals("1", employee.getId());
        assertEquals("John Doe", employee.getName());
        assertEquals("50000", employee.getSalary());
        assertEquals("30", employee.getAge());
        assertEquals("", employee.getProfileImage());
    }

    @Test
    void testEmployeeResponse() {
        EmployeeResponse response = new EmployeeResponse();
        EmployeeDTO employeeDTO = new EmployeeDTO("1", "John Doe", "50000", "30", "");

        response.setStatus("success");
        response.setData(employeeDTO);
        response.setMessage("Employee found");

        assertEquals("success", response.getStatus());
        assertEquals(employeeDTO, response.getEmployee());
        assertEquals("Employee found", response.getMessage());
    }

    @Test
    void testEmployeeResponseWithList() {
        EmployeeResponse response = new EmployeeResponse();
        List<EmployeeDTO> employeeDTOs = Arrays.asList(
                new EmployeeDTO("1", "John Doe", "50000", "30", ""),
                new EmployeeDTO("2", "Jane Doe", "60000", "35", "")
        );

        response.setStatus("success");
        response.setData(employeeDTOs);
        response.setMessage("Employees found");

        assertEquals("success", response.getStatus());
        assertEquals(employeeDTOs, response.getEmployees());
        assertEquals("Employees found", response.getMessage());
    }
}