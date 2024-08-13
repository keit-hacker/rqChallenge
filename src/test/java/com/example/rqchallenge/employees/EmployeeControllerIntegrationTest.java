package com.example.rqchallenge.employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllEmployees() throws Exception {
        MvcResult result = mockMvc.perform(get("/employees")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("API Response for getAllEmployees: " + content);

        mockMvc.perform(get("/employees")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getEmployeeById() throws Exception {
        MvcResult result = mockMvc.perform(get("/employee/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("API Response for getEmployeeById: " + content);

        mockMvc.perform(get("/employee/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void getEmployeesByNameSearch() throws Exception {
        MvcResult result = mockMvc.perform(get("/search/{searchString}", "John"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("API Response for getEmployeesByNameSearch: " + content);

        mockMvc.perform(get("/search/{searchString}", "John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        if (!content.equals("[]")) {
            mockMvc.perform(get("/search/{searchString}", "John"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0]").exists());
        }
    }

    @Test
    void getHighestSalaryOfEmployees() throws Exception {
        MvcResult result = mockMvc.perform(get("/highestSalary"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("API Response for getHighestSalaryOfEmployees: " + content);

        mockMvc.perform(get("/highestSalary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber());
    }

    @Test
    void getTopTenHighestEarningEmployeeNames() throws Exception {
        MvcResult result = mockMvc.perform(get("/topTenHighestEarningEmployeeNames"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("API Response for getTopTenHighestEarningEmployeeNames: " + content);

        mockMvc.perform(get("/topTenHighestEarningEmployeeNames"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(lessThanOrEqualTo(10)));
    }

    @Test
    void createEmployee() throws Exception {
        Map<String, String> newEmployee = new HashMap<>();
        newEmployee.put("name", "John Doe");
        newEmployee.put("salary", "50000");
        newEmployee.put("age", "30");

        MvcResult result = mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEmployee)))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("API Response for createEmployee: " + content);

        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEmployee)))
                .andExpect(status().isOk())
                .andExpect(content().string(notNullValue()));
    }
}