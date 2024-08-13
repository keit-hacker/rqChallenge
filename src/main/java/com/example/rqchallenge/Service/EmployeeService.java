package com.example.rqchallenge.Service;

import com.example.rqchallenge.dto.EmployeeDTO;
import com.example.rqchallenge.exception.EmployeeNotFoundException;
import com.example.rqchallenge.exception.EmployeeCreationException;
import com.example.rqchallenge.exception.EmployeeDeletionException;
import com.example.rqchallenge.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        logger.info("EmployeeService initialized");
    }

    public List<EmployeeDTO> getAllEmployees(int page, int size) {
        logger.info("Fetching employees page {} with size {}", page, size);
        List<EmployeeDTO> allEmployees = employeeRepository.getAllEmployees();
        int start = page * size;
        int end = Math.min((start + size), allEmployees.size());
        List<EmployeeDTO> paginatedEmployees = allEmployees.subList(start, end);
        logger.info("Retrieved {} employees out of {}", paginatedEmployees.size(), allEmployees.size());
        return paginatedEmployees;
    }

    public EmployeeDTO getEmployeeById(String id) {
        logger.info("Fetching employee with id: {}", id);
        EmployeeDTO employee = employeeRepository.getEmployeeById(id);
        if (employee == null) {
            logger.warn("Employee not found with id: {}", id);
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
        return employee;
    }

    public List<EmployeeDTO> getEmployeesByNameSearch(String searchString) {
        logger.info("Searching for employees with name containing: {}", searchString);
        List<EmployeeDTO> employees = employeeRepository.searchEmployeesByName(searchString);
        logger.info("Found {} employees matching search string: {}", employees.size(), searchString);
        return employees;
    }

    public Integer getHighestSalaryOfEmployees() {
        logger.info("Calculating highest salary of employees");
        Integer highestSalary = employeeRepository.getHighestSalary();
        logger.info("Highest salary found: {}", highestSalary);
        return highestSalary;
    }

    public List<String> getTopTenHighestEarningEmployeeNames() {
        logger.info("Fetching top 10 highest earning employee names");
        List<String> topEmployees = employeeRepository.getTopTenHighestEarningEmployeeNames();
        logger.info("Retrieved top {} highest earning employee names", topEmployees.size());
        return topEmployees;
    }

    public String createEmployee(String name, String salary, String age) {
        logger.info("Creating new employee: name={}, salary={}, age={}", name, salary, age);
        try {
            String newId = employeeRepository.createEmployee(name, salary, age);
            logger.info("Created new employee with id: {}", newId);
            return newId;
        } catch (Exception e) {
            logger.error("Error creating new employee", e);
            throw new EmployeeCreationException("Failed to create employee", e);
        }
    }

    public String deleteEmployeeById(String id) {
        logger.info("Deleting employee with id: {}", id);
        try {
            String result = employeeRepository.deleteEmployee(id);
            if ("employee not found".equals(result)) {
                logger.warn("Attempted to delete non-existent employee with id: {}", id);
                throw new EmployeeNotFoundException("Employee not found with id: " + id);
            }
            logger.info("Deleted employee with id: {}", id);
            return result;
        } catch (Exception e) {
            logger.error("Error deleting employee with id: {}", id, e);
            throw new EmployeeDeletionException("Failed to delete employee with id: " + id, e);
        }
    }
}