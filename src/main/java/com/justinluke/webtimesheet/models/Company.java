package com.justinluke.webtimesheet.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by there on 8/3/2017.
 */
public class Company {

    @NotNull
    @Size(min = 3, max = 55, message = "Please enter a valid name")
    private String name;

    private List<Shift> shifts = new ArrayList<>();

    private int companyId;
    private static int nextId = 1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompanyId() {
        return companyId;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void addShift(Shift shift) {
        this.shifts.add(shift);
    }

    public void removeShift (int shiftId) {
        for (Shift shift : this.shifts) {
            if (shift.getShiftId() == shiftId) {
                this.shifts.remove(shift);
            }
        }
    }

    public Company() {
        companyId = nextId;
        nextId++;
    }

    public Company(String name) {
        this();
        this.name = name;
    }
}
