package com.example.spring_demo.rest.employee;

// Highest level of the n-tier design pattern - fulfills the client HTTP request.
// This is the REST endpoint that will be called when a client submits an HTTP request with the annotated mapping.
// This layer will transform the Java object to JSON using the Jackson library and vice-versa.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/employee")
public class EmployeeController {
    // wire up EmployeeService for dependency injection
    EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    // Configure a GET endpoint to get all employees
    // Method signature returns a List object which will be transformed into JSON.
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }
}
