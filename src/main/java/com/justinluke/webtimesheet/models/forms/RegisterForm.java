package com.justinluke.webtimesheet.models.forms;

import javax.validation.constraints.NotNull;

/**
 * Created by there on 8/22/2017.
 */
public class RegisterForm extends LoginForm {

    @NotNull(message = "Passwords do not match")
    private String verifyPassword;

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
        checkPasswordForRegistration();
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPasswordForRegistration();
    }

    private void checkPasswordForRegistration() {
        if (!getPassword().equals(verifyPassword)) {
            verifyPassword = null;
        }
    }
}