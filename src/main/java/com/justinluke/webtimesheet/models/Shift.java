package com.justinluke.webtimesheet.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by there on 1/2/2018.
 */
@Entity
public class Shift {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String date;

    @NotNull
    private String timeIn;

    @NotNull
    private String timeOut;

    @ManyToOne
    private Company company;

    @ManyToOne
    private User user;

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public Company getCompany() {
        return company;
    }

    public User getUser() {
        return user;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Shift() {
    }

    public Shift(String date, String timeIn, String timeOut, Company company, User user) {
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.company = company;
        this.user = user;
    }
}
