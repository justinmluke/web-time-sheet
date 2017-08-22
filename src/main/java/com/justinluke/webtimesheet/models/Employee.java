package com.justinluke.webtimesheet.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by there on 8/14/2017.
 */
public class Employee extends User {

    @NotNull(message = "Please enter a valid first name")
    private String firstName;

    @NotNull(message = "Please enter a valid last name")
    private String lastName;

    private List<Company> companies = new ArrayList<>();

    @Id
    @GeneratedValue
    private int id;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Iterable<Company> getCompanies() {
        return companies;
    }

    public void addCompany (Company company) {
        this.companies.add(company);
    }

    public int getEmployeeId() {
        return id;
    }

    public Employee() {}

    public Employee(String email, String password, String verifyPassword, String firstName, String lastName) {
        super(email, password, verifyPassword);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
