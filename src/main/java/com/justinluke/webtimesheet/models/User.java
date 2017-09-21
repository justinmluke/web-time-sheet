package com.justinluke.webtimesheet.models;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
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

    public List<Company> getCompanies() {
        return this.companies;
    }

    public void addCompany(Company company) {
        companies.add(company);
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
