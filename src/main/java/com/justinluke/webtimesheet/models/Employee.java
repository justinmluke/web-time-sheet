package com.justinluke.webtimesheet.models;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

/**
 * Created by there on 8/14/2017.
 */
public class Employee extends User {

    @NotNull(message = "Please enter a valid first name")
    private String firstName;

    @NotNull(message = "Please enter a valid last name")
    private String lastName;

    private Iterable<Company> companies = new ArrayList<>();
}
