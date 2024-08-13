package com.example.rqchallenge.employees;

import com.example.rqchallenge.dto.EmployeeDTO;
import com.example.rqchallenge.Service.EmployeeService;
import com.example.rqchallenge.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Fetching all employees, page: {}, size: {}", page, size);
        List<EmployeeDTO> employees = employeeService.getAllEmployees(page, size);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable String id) {
        logger.info("Fetching employee with id: {}", id);
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/search/{searchString}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByNameSearch(@PathVariable String searchString) {
        logger.info("Searching employees with name containing: {}", searchString);
        List<EmployeeDTO> employees = employeeService.getEmployeesByNameSearch(searchString);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/highestSalary")
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        logger.info("Fetching highest salary of employees");
        Integer highestSalary = employeeService.getHighestSalaryOfEmployees();
        return ResponseEntity.ok(highestSalary);
    }

    @GetMapping("/topTenHighestEarningEmployeeNames")
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        logger.info("Fetching top 10 highest earning employee names");
        List<String> topEmployees = employeeService.getTopTenHighestEarningEmployeeNames();
        return ResponseEntity.ok(topEmployees);
    }

    @PostMapping()
    public ResponseEntity<String> createEmployee(@RequestBody Map<String, String> employeeInput) {
        logger.info("Creating new employee");
        String name = employeeInput.get("name");
        String salary = employeeInput.get("salary");
        String age = employeeInput.get("age");
        if (name == null || salary == null || age == null) {
            return ResponseEntity.badRequest().body("Name, Salary and Age of employee are required");
        }
        String result = employeeService.createEmployee(name, salary, age);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable String id) {
        logger.info("Deleting employee with id: {}", id);
        String result = employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok(result);
    }
}