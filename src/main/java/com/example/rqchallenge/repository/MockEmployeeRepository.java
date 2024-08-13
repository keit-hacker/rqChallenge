package com.example.rqchallenge.repository;

import com.example.rqchallenge.dto.EmployeeDTO;
import com.example.rqchallenge.mock.MockEmployeeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class MockEmployeeRepository implements EmployeeRepository {
    private static final Logger logger = LoggerFactory.getLogger(MockEmployeeRepository.class);
    private final List<EmployeeDTO> employees;

    public MockEmployeeRepository() {
        this.employees = new ArrayList<>(MockEmployeeData.getMockedEmployees());
        logger.info("MockEmployeeRepository initialized with {} employees", employees.size());
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        logger.info("Fetching all employees. Total count: {}", employees.size());
        return new ArrayList<>(employees);
    }

    @Override
    public EmployeeDTO getEmployeeById(String id) {
        logger.info("Fetching employee with id: {}", id);
        return employees.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<EmployeeDTO> searchEmployeesByName(String searchString) {
        logger.info("Searching for employees with name containing: {}", searchString);
        return employees.stream()
                .filter(emp -> emp.getName().toLowerCase().contains(searchString.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public String createEmployee(String name, String salary, String age) {
        String newId = UUID.randomUUID().toString();
        EmployeeDTO newEmployee = new EmployeeDTO(newId, name, salary, age, "");
        employees.add(newEmployee);
        logger.info("Created new employee with ID: {}", newId);
        return newId;
    }

    @Override
    public String deleteEmployee(String id) {
        logger.info("Attempting to delete employee with id: {}", id);
        boolean removed = employees.removeIf(emp -> emp.getId().equals(id));
        if (removed) {
            logger.info("Successfully deleted employee with id: {}", id);
            return "successfully deleted";
        } else {
            logger.warn("Failed to delete employee. Employee with id {} not found", id);
            return "employee not found";
        }
    }

    @Override
    public Integer getHighestSalary() {
        logger.info("Calculating highest salary of employees");
        return employees.stream()
                .mapToInt(emp -> Integer.parseInt(emp.getSalary()))
                .max()
                .orElse(0);
    }

    @Override
    public List<String> getTopTenHighestEarningEmployeeNames() {
        logger.info("Fetching top 10 highest earning employee names");
        return employees.stream()
                .sorted((e1, e2) -> Integer.compare(Integer.parseInt(e2.getSalary()), Integer.parseInt(e1.getSalary())))
                .limit(10)
                .map(EmployeeDTO::getName)
                .collect(Collectors.toList());
    }
}