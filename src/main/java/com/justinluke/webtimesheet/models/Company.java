package com.justinluke.webtimesheet.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by there on 8/3/2017.
 */

@Entity
public class Company {

    @NotNull
    @Size(min = 3, max = 55, message = "Please enter a valid name")
    private String name;

    @Id
    @GeneratedValue
    private int id;

    @ManyToMany(mappedBy = "companies")
    private List<User> user;

    @OneToMany
    @JoinColumn(name = "company_id")
    private List<Shift> shifts = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public List<Shift> getShifts() {return shifts;}

    public Company() {}

    public Company(String name) {
        this.name = name;
    }
}
