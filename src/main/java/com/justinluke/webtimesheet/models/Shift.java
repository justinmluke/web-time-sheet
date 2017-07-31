package com.justinluke.webtimesheet.models;



import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * Created by there on 7/15/2017.
 */
public class Shift {

    @NotNull
    private LocalDate date = LocalDateTime.now().toLocalDate();

    @NotNull
    private String clockedIn;

    @NotNull
    private String clockedOut;

    private int shiftId;
    private static int nextId = 1;

    private int hoursWorked = 0;

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

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public Shift(LocalDate date) {
        this.date = date;
    }

    public Shift(LocalDate date, String clockedIn, String clockedOut) {
        this.date = date;
        this.clockedIn = clockedIn;
        this.clockedOut = clockedOut;
    }

    public Shift(LocalDate date, String clockedIn, String clockedOut, int hoursWorked) {
        this.date = date;
        this.clockedIn = clockedIn;
        this.clockedOut = clockedOut;
        this.hoursWorked = hoursWorked;
    }

    public Shift() {
        shiftId = nextId;
        nextId++;
    }
}



