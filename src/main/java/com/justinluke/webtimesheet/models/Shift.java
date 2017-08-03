package com.justinluke.webtimesheet.models;



import javax.validation.constraints.NotNull;
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
        this();
        this.date = date;
        this.clockedIn = clockedIn;
        this.clockedOut = clockedOut;
    }

    public Shift() {
        shiftId = nextId;
        nextId++;
    }
}



