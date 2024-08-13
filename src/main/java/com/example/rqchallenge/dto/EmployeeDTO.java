package com.example.rqchallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDTO {
    private String id;

    @JsonProperty("employee_name")
    private String name;

    @JsonProperty("employee_salary")
    private String salary;

    @JsonProperty("employee_age")
    private String age;

    @JsonProperty("profile_image")
    private String profileImage;

    public EmployeeDTO() {}

    public EmployeeDTO(String id, String name, String salary, String age, String profileImage) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.profileImage = profileImage;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }

    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }

    public String getProfileImage() { return profileImage; }
    public void setProfileImage(String profileImage) { this.profileImage = profileImage; }
}