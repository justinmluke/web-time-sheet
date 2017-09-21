package com.justinluke.webtimesheet.models.forms;

import com.justinluke.webtimesheet.models.Company;
import com.justinluke.webtimesheet.models.User;

import javax.validation.constraints.NotNull;

/**
 * Created by there on 9/21/2017.
 */
public class AddEmployerForm {

    private User user;
    private Iterable<Company> companies;

    @NotNull
    private int userId;

    @NotNull
    private int companyId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public AddEmployerForm() {}

    public AddEmployerForm(User user, Iterable<Company> companies) {
        this.user = user;
        this.companies = companies;
    }
}