package com.example.spring_demo.rest.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


// Middle layer in the n-tier design pattern.
// The service layer is where the business logic goes.
// connects to the DAO/Repository class and fulfills requests from controller class.

@Service
public class EmployeeServiceImpl implements EmployeeService {
    // declare a dependency which Spring will inject automatically
    EmployeeRepository employeeRepository;
    // Inject class dependencies (the repository object)
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    // expose API for the controller to retrieve data
    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
    // add method to CREATE or UPDATE a record
    @Override
    public void saveEmployee(Employee employee) {
        // The controller will call this method with the constructed Employee object.
        // The controller will take the object from the JSON request body and use Jackson to
        // deserialize the JSON data to instantiate the Employee object.
        // The Spring Data repository will then take the employee object
        // and use Hibernate which implements the JPA specification
        // to map the object properties into the relational database (ORM).
        // Many layers at work here but the code is very short and simple.
        // This will also UPDATE an Employee record when passing in the primary key
        // and updated data through the POST request body.
        System.out.println("EmployeeService: Saving employee object.");
        employeeRepository.save(employee);
    }
    // delete employee
    @Override
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }
    // get single employee record
    @Override
    public Employee getEmployeeById(long id) {
        // Optional is a class introduced in Java 8 to handle null values in a different, hopefully better, pattern.
        // You still have to check for nulls by using isPresent() but I suppose the idea is that you can better
        // represent what null would mean in this context, and it forces you to be more explicit in your designs.
        // It forces you to consider what to return to the absent-case whereas null can be used to denote an error
        // or an absent value or a bug.
        // There have also been criticism about the get() method on the Optional class. If you invoke it on
        // an empty (i.e. null) Optional object you still get a NullPointerException which isn't any better
        // than just using nulls in the first place. So essentially, Optional is a tool to express your
        // intent in a more explicit way (denoting the absent-case of a value) and utilize the public methods
        // of Optional to work with null (absent-values) in a more explicit way.
        Optional<Employee> queryResult = employeeRepository.findById(id);
        if (!queryResult.isPresent()) {
            // The specified employee primary key ID is not present in the database.
            // Respond with a 400 level error indicating this in the controller.
            // Here throw an error so the controller can catch it in a try-catch block.
            throw new NoSuchElementException();
        }
        // else return the query result
        else {
            return queryResult.get();
        }
    }
}
