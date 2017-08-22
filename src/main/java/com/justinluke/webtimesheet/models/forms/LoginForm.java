package com.justinluke.webtimesheet.models.forms;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by there on 8/22/2017.
 */
public class LoginForm {

    @NotNull
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotNull
    @Pattern(regexp = "(\\S){4,20}", message = "Password must have 4-20 non-whitespace characters")
    private String password;

    public LoginForm () {}

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
    }
}
