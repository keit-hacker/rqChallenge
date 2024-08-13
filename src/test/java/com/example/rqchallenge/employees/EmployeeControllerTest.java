package com.example.rqchallenge.employees;

import com.example.rqchallenge.Service.EmployeeService;
import com.example.rqchallenge.dto.EmployeeDTO;
import com.example.rqchallenge.exception.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEmployees() {
        List<EmployeeDTO> mockEmployees = Arrays.asList(
                new EmployeeDTO("1", "John Doe", "50000", "30", ""),
                new EmployeeDTO("2", "Jane Doe", "60000", "35", "")
        );
        when(employeeService.getAllEmployees(0, 10)).thenReturn(mockEmployees);

        ResponseEntity<List<EmployeeDTO>> response = employeeController.getAllEmployees(0, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void getEmployeeById() {
        EmployeeDTO mockEmployee = new EmployeeDTO("1", "John Doe", "50000", "30", "");
        when(employeeService.getEmployeeById("1")).thenReturn(mockEmployee);

        ResponseEntity<EmployeeDTO> response = employeeController.getEmployeeById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getName());
    }

    @Test
    void getEmployeeById_NotFound() {
        when(employeeService.getEmployeeById("999")).thenThrow(new EmployeeNotFoundException("Employee not found"));

        assertThrows(EmployeeNotFoundException.class, () -> employeeController.getEmployeeById("999"));
    }

    @Test
    void getEmployeesByNameSearch() {
        List<EmployeeDTO> mockEmployees = Arrays.asList(
                new EmployeeDTO("1", "John Doe", "50000", "30", ""),
                new EmployeeDTO("2", "Johnny Smith", "60000", "35", "")
        );
        when(employeeService.getEmployeesByNameSearch("John")).thenReturn(mockEmployees);

        ResponseEntity<List<EmployeeDTO>> response = employeeController.getEmployeesByNameSearch("John");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void getHighestSalaryOfEmployees() {
        when(employeeService.getHighestSalaryOfEmployees()).thenReturn(100000);

        ResponseEntity<Integer> response = employeeController.getHighestSalaryOfEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(100000, response.getBody());
    }

    @Test
    void getTopTenHighestEarningEmployeeNames() {
        List<String> mockNames = Arrays.asList("John Doe", "Jane Smith", "Bob Johnson");
        when(employeeService.getTopTenHighestEarningEmployeeNames()).thenReturn(mockNames);

        ResponseEntity<List<String>> response = employeeController.getTopTenHighestEarningEmployeeNames();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, response.getBody().size());
        assertEquals("John Doe", response.getBody().get(0));
    }

    @Test
    void createEmployee() {
        Map<String, String> employeeInput = new HashMap<>();
        employeeInput.put("name", "John Doe");
        employeeInput.put("salary", "50000");
        employeeInput.put("age", "30");

        when(employeeService.createEmployee("John Doe", "50000", "30")).thenReturn("1");

        ResponseEntity<String> response = employeeController.createEmployee(employeeInput);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", response.getBody());
    }

    @Test
    void createEmployeeInvalidInput() {
        Map<String, String> employeeInput = new HashMap<>();
        employeeInput.put("name", "John Doe");
        // Missing salary and age

        ResponseEntity<String> response = employeeController.createEmployee(employeeInput);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Name, Salary and Age of employee are required", response.getBody());
    }

    @Test
    void deleteEmployeeById() {
        when(employeeService.deleteEmployeeById("1")).thenReturn("Employee deleted");

        ResponseEntity<String> response = employeeController.deleteEmployeeById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Employee deleted", response.getBody());
    }
}