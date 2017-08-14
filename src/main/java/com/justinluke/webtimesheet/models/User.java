package com.justinluke.webtimesheet.models;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by there on 8/14/2017.
 */
public abstract class User {

    @Email(message = "Please enter a valid email")
    private String email;

    @NotNull(message = "Please enter a valid password")
    @Size(min = 6, max = 24, message = "Password must be between than 6 characters")
    private String password;

    @NotNull(message = "Passwords do not match")
    private String verifyPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword(password);
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPassword(password);
    }

    public User() {}

    public User(String email, String password, String verifyPassword) {
        this.email = email;
        this.password = password;
        this.verifyPassword = verifyPassword;
    }

    public boolean checkPassword(String password) {
        if (password != null &&
                verifyPassword != null){
            if (!password.equals(verifyPassword)){
                setVerifyPassword(null);
            }
        } else {
            return true;
        }
        return false;
    }

}
