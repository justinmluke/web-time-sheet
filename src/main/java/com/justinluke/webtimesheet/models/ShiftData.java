package com.justinluke.webtimesheet.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by there on 7/28/2017.
 */
public class ShiftData {

    static List<Shift> shifts = new ArrayList<>();

    public static List<Shift> getAll() {
        return shifts;
    }

    public static void add(Shift newShift) {
        shifts.add(newShift);
    }

    public static void remove(int id) {
        Shift shiftToRemove = getById(id);
        shifts.remove(shiftToRemove);
    }

    public static Shift getById (int id) {

        Shift theShift = null;

        for (Shift candidateShift : shifts) {
            if (candidateShift.getShiftId() == id) {
                candidateShift = theShift;
            }
        }
        return theShift;
    }
}
