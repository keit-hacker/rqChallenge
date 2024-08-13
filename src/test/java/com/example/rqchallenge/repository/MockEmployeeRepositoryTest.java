package com.example.rqchallenge.repository;

import com.example.rqchallenge.dto.EmployeeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MockEmployeeRepositoryTest {

    private MockEmployeeRepository repository;

    @BeforeEach
    void setUp() {
        repository = new MockEmployeeRepository();
    }

    @Test
    void getAllEmployees() {
        List<EmployeeDTO> employees = repository.getAllEmployees();
        assertFalse(employees.isEmpty());
        assertTrue(employees.size() >= 24); // Based on the initial mock data
    }

    @Test
    void getEmployeeById_Exists() {
        EmployeeDTO employee = repository.getEmployeeById("1");
        assertNotNull(employee);
        assertEquals("1", employee.getId());
        assertEquals("Tiger Nixon", employee.getName());
    }

    @Test
    void getEmployeeById_NotExists() {
        EmployeeDTO employee = repository.getEmployeeById("999");
        assertNull(employee);
    }

    @Test
    void searchEmployeesByName() {
        List<EmployeeDTO> employees = repository.searchEmployeesByName("Nixon");
        assertFalse(employees.isEmpty());
        assertTrue(employees.stream().anyMatch(emp -> emp.getName().contains("Nixon")));
    }

    @Test
    void createEmployee() {
        String newId = repository.createEmployee("John Doe", "100000", "30");
        assertNotNull(newId);
        EmployeeDTO newEmployee = repository.getEmployeeById(newId);
        assertNotNull(newEmployee);
        assertEquals("John Doe", newEmployee.getName());
        assertEquals("100000", newEmployee.getSalary());
        assertEquals("30", newEmployee.getAge());
    }

    @Test
    void deleteEmployee_Exists() {
        String result = repository.deleteEmployee("1");
        assertEquals("successfully deleted", result);
        assertNull(repository.getEmployeeById("1"));
    }

    @Test
    void deleteEmployee_NotExists() {
        String result = repository.deleteEmployee("999");
        assertEquals("employee not found", result);
    }

    @Test
    void getHighestSalary() {
        Integer highestSalary = repository.getHighestSalary();
        assertNotNull(highestSalary);
        assertTrue(highestSalary > 0);
    }

    @Test
    void getTopTenHighestEarningEmployeeNames() {
        List<String> topEarners = repository.getTopTenHighestEarningEmployeeNames();
        assertNotNull(topEarners);
        assertTrue(topEarners.size() <= 10);
        assertFalse(topEarners.isEmpty());
    }
}