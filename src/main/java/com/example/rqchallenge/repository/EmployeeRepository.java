package com.example.rqchallenge.repository;

import com.example.rqchallenge.dto.EmployeeDTO;
import java.util.List;

public interface EmployeeRepository {
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(String id);
    List<EmployeeDTO> searchEmployeesByName(String searchString);
    String createEmployee(String name, String salary, String age);
    String deleteEmployee(String id);
    Integer getHighestSalary();
    List<String> getTopTenHighestEarningEmployeeNames();
}