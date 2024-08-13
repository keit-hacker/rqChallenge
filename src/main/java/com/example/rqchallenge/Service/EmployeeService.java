package com.example.rqchallenge.Service;

import com.example.rqchallenge.dto.EmployeeDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmployeeService {
    private final String BASE_URL = "";
    private final RestTemplate restTemplate;

    public EmployeeService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<EmployeeDTO> getAllEmployees(){
        //logic
    }

    public EmployeeDTO getEmployeeById(String id){
        //logic
    }

    public Integer getHighestSalaryOfEmployees(){
        //logic
    }

    public List<String> getTopTenHighestEarningEmployeesNames() {
        //logic
    }

    public String createEmplyoee(String name, String salary, String age){
        //logic
    }

    public String deleteEmployeeById(String id){
        //logic
    }
}
