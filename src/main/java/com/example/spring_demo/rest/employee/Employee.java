package com.example.spring_demo.rest.employee;

import javax.persistence.*;

@Entity
@Table(name="Employees")
public class Employee {
    // Hibernate will map these fields to the Employee table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tells Hibernate to use the identity column in DB (auto-increment)
    private Long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    // no need for column annotation here because the variable name maps directly to DB
    private String email;

    // The JPA spec requires a no arg constructor for when it instantiates the object.
    public Employee() {}

    // create a constructor for us when we instantiate a new object to save to DB
    // this is not needed for the basic get request
//    public Employee(String firstName, String lastName, String email) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//    }

    // need JUST GETTERS here otherwise Spring will not build the ResponseObject in the controller
    // Including the auto-generated ID. When I didn't include the GETTERS, the JSON response was empty.
    // Setters were not needed for this but may be needed for a POST (create employee)?
    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getFirstName() {
        return firstName;
    }

//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }

    public String getLastName() {
        return lastName;
    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }

    public String getEmail() {
        return email;
    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}
