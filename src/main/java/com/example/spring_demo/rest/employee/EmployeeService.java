package com.example.spring_demo.rest.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Middle layer in the n-tier design pattern.
// The service layer is where the business logic goes.
// connects to the DAO/Repository class and fulfills requests from controller class.

@Service
public class EmployeeService {
    // declare a dependency which Spring will inject automatically
    EmployeeRepository employeeRepository;
    // Inject class dependencies (the repository object)
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    // expose API for the controller to retrieve data
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
}
