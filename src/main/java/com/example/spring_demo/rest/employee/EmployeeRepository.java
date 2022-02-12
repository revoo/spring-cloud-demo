package com.example.spring_demo.rest.employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

// Lowest level before the actual database in the n-tier design pattern. Implements the DAL (Data Access Layer).
// Most confusing part - heavily abstracted.
// Spring data JPA will implement this interface with a few standard methods like findAll and save.
// Spring will implement this interface depending on what the interface extends.
// CrudRepository is the most basic interface and just provides basic CRUD support.
// The actual implementation will be injected by Spring in the service layer (EmployeeService).
// Hibernate works under the hood to interface with JDBC to construct and run the SQL queries.
// The Repository design pattern is similar to the Java EE pattern of Data Access Object (DAO).
// Repositories are closer to domain-driven design and are more abstract. DAO is more of a Java term.
// Both are often used interchangeably but DAO's are typically narrower in scope and are used for just one entity.

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    // CrudRepository findAll returns an Iterable object instead of a List
    // JpaRepository returns a list, but I want to show here that you can override the method signature
    // And Spring will adjust accordingly
    @Override
    @NonNull
    List<Employee> findAll();
}
