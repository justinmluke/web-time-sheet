package com.justinluke.webtimesheet.models;

/**
 * Created by there on 8/3/2017.
 */
public class Company {

    private String name;
    private int companyId;
    private static int nextId = 1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompanyId() {
        return companyId;
    }

    public Company() {
        companyId = nextId;
        nextId++;
    }

    public Company(String name) {
        this.name = name;
    }
}
