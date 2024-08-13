package com.example.rqchallenge.Service;

import com.example.rqchallenge.dto.EmployeeDTO;
import com.example.rqchallenge.exception.EmployeeDeletionException;
import com.example.rqchallenge.exception.EmployeeNotFoundException;
import com.example.rqchallenge.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

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
        when(employeeRepository.getAllEmployees()).thenReturn(mockEmployees);

        List<EmployeeDTO> result = employeeService.getAllEmployees(0, 10);

        assertEquals(2, result.size());
    }

    @Test
    void getEmployeeById() {
        EmployeeDTO mockEmployee = new EmployeeDTO("1", "John Doe", "50000", "30", "");
        when(employeeRepository.getEmployeeById("1")).thenReturn(mockEmployee);

        EmployeeDTO result = employeeService.getEmployeeById("1");

        assertEquals("John Doe", result.getName());
    }

    @Test
    void getEmployeeById_NotFound() {
        when(employeeRepository.getEmployeeById("999")).thenReturn(null);

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById("999"));
    }

    @Test
    void getEmployeesByNameSearch() {
        List<EmployeeDTO> mockEmployees = Arrays.asList(
                new EmployeeDTO("1", "John Doe", "50000", "30", ""),
                new EmployeeDTO("2", "Johnny Smith", "60000", "35", "")
        );
        when(employeeRepository.searchEmployeesByName("John")).thenReturn(mockEmployees);

        List<EmployeeDTO> result = employeeService.getEmployeesByNameSearch("John");

        assertEquals(2, result.size());
    }

    @Test
    void getHighestSalaryOfEmployees() {
        when(employeeRepository.getHighestSalary()).thenReturn(100000);

        Integer result = employeeService.getHighestSalaryOfEmployees();

        assertEquals(100000, result);
    }

    @Test
    void getTopTenHighestEarningEmployeeNames() {
        List<String> mockNames = Arrays.asList("John Doe", "Jane Smith", "Bob Johnson");
        when(employeeRepository.getTopTenHighestEarningEmployeeNames()).thenReturn(mockNames);

        List<String> result = employeeService.getTopTenHighestEarningEmployeeNames();

        assertEquals(3, result.size());
        assertEquals("John Doe", result.get(0));
    }

    @Test
    void createEmployee() {
        when(employeeRepository.createEmployee("John Doe", "50000", "30")).thenReturn("1");

        String result = employeeService.createEmployee("John Doe", "50000", "30");

        assertEquals("1", result);
    }

    @Test
    void deleteEmployeeById() {
        when(employeeRepository.deleteEmployee("1")).thenReturn("successfully deleted");

        String result = employeeService.deleteEmployeeById("1");

        assertEquals("successfully deleted", result);
    }

    @Test
    void deleteEmployeeById_NotFound() {
        when(employeeRepository.deleteEmployee("999")).thenReturn("employee not found");

        assertThrows(EmployeeDeletionException.class, () -> employeeService.deleteEmployeeById("999"));
    }
}