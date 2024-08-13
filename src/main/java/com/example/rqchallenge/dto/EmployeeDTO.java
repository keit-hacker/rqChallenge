package com.example.rqchallenge.dto;

public class EmployeeDTO {

    private int id;
    private String name;
    private int salary;
    private int age;

    //Parameterized Constructor
    public EmployeeDTO(int id, String name, int salary, int age){
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }


// Getters
    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getSalary(){
        return salary;
    }

    public int getAge(){
        return age;
    }

// Setters
    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSalary(int salary){
        this.salary = salary;
    }

    public void setAge(int age){
        this.age = age;
    }
}