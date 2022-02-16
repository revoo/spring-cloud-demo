package com.example.spring_demo.rest.employee;

// Highest level of the n-tier design pattern - fulfills the client HTTP request.
// This is the REST endpoint that will be called when a client submits an HTTP request with the annotated mapping.
// This layer will transform the Java object to JSON using the Jackson library and vice-versa.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path="/api/v1/employee")
public class EmployeeController {
    // wire up EmployeeService for dependency injection
    EmployeeServiceImpl employeeService;
    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }
    // Configure a GET endpoint to get all employees
    // Method signature returns a List object which will be transformed into JSON.
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }
    // CREATE or UPDATE POST Endpoint
    @PostMapping
    public void saveEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
    }
    // GET employee by ID
    // To customize the response - we return a ResponseEntity<T> object.
    // This object represents the entire response back including the HTTP status code (400, 500, etc..),
    // The headers we sent back, and of course, the response body (e.g. JSON response or something else).
    // We also append a PathVariable to the getMapping. This will add this suffix to the end of the already
    // Defined RequestMapping of /api/v1/employee. This value then gets injected into the employeeId method parameter.
    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long employeeId) {
        Employee returnedEmployee = null;
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            returnedEmployee = employeeService.getEmployeeById(employeeId);
        } catch (NoSuchElementException ex) {
            // set the HTTP status code to not found
            httpStatus = HttpStatus.NOT_FOUND;
        }
        // return response with returnedEmployee as the response body and the HttpStatus
        return new ResponseEntity<Employee>(returnedEmployee, httpStatus);
    }
    // Delete an Employee by their primary key (ID)

}
