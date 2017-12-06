package com.justinluke.webtimesheet.models.forms;

import com.justinluke.webtimesheet.models.Company;
import com.justinluke.webtimesheet.models.User;

import javax.validation.constraints.NotNull;

/**
 * Created by there on 12/6/2017.
 */
public class AddShiftForm {

    private User user;

    private Company company;

    @NotNull
    private int userId;

    @NotNull
    private int companyId;

    @NotNull
    private int shiftId;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }


    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

}