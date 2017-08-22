package com.justinluke.webtimesheet.models;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by there on 8/14/2017.
 */
public class User extends AbstractEntity{

    @Email(message = "Please enter a valid email")
    private String email;

    @NotNull
    private String pwHash;
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull(message = "Passwords do not match")
    private String verifyPassword;

    @ManyToMany
    private List<Company> companies = new ArrayList<>();

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
