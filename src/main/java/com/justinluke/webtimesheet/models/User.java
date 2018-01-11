package com.justinluke.webtimesheet.models;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by there on 8/14/2017.
 */
@Entity
public class User extends AbstractEntity{

    @Email(message = "Please enter a valid email")
    private String email;

    @NotNull
    private String pwHash;
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @ManyToMany
    private List<Company> companies;

    @OneToMany
    @JoinColumn(name = "uid")
    private List<Shift> shifts;

    public List<Company> getCompanies() {
        return this.companies;
    }

    public List<Shift> getShifts() {
        return this.shifts;
    }

    public void addCompany(Company company) {
        companies.add(company);
    }

    public void addShift(Shift shift) {
        shifts.add(shift);
    }

    public List<Shift> findShiftsbyCompany (Company company) {
        List<Shift> theShifts = new ArrayList<>();
        for (Shift aShift : this.getShifts()) {
            if (aShift.getCompany() == company) {
                theShifts.add(aShift);
            }
        }
        return theShifts;
    }

    public String getEmail() {
        return email;
    }

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.pwHash = hashPassword(password);
    }

    private static String hashPassword(String password) {
        return encoder.encode(password);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }
}
