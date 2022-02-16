package com.example.spring_demo.rest.employee;

// Having this as an interface with allow us to swap between different service implementations.
// In this instance we only have one implementation of EmployeeService, so it's not terribly useful.
// this is considered a best practice to 'code to an interface'.
// Having this set up will allow for easier testing through mocking.
// There is also the principle of YAGNI that says that this is extraneous.
// However, this interface forces you to define a contract and make strong guarantees about your code. A valuable practice.
// There are arguments for both sides - need to research more.

import java.util.List;

public interface EmployeeService {
    public List<Employee> getEmployees();
    public void saveEmployee(Employee employee);
    public void deleteEmployeeById(long id);
    public Employee getEmployeeById(long id);
}
