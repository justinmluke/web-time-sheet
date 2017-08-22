package com.justinluke.webtimesheet.models;



import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * Created by there on 7/15/2017.
 */
@Entity
public class Shift {

    @NotNull
    private LocalDate date = LocalDateTime.now().toLocalDate();

    @NotNull
    private String clockedIn;

    @NotNull
    private String clockedOut;

    @ManyToOne
    private Company company;

    @Id
    @GeneratedValue
    private int id;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getClockedIn() {
        return clockedIn;
    }

    public void setClockedIn(String clockedIn) {
        this.clockedIn = clockedIn;
    }

    public String getClockedOut() {
        return clockedOut;
    }

    public void setClockedOut(String clockedOut) {
        this.clockedOut = clockedOut;
    }

    public int getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Shift(LocalDate date) {
        this.date = date;
    }

    public Shift(LocalDate date, String clockedIn, String clockedOut, Company company) {
        this.date = date;
        this.clockedIn = clockedIn;
        this.clockedOut = clockedOut;
        this.company = company;
    }

    public Shift() {}
}



