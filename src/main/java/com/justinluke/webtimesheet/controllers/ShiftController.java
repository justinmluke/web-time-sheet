package com.justinluke.webtimesheet.controllers;

import com.justinluke.webtimesheet.models.Company;
import com.justinluke.webtimesheet.models.CompanyData;
import com.justinluke.webtimesheet.models.Shift;
import com.justinluke.webtimesheet.models.ShiftData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by there on 7/15/2017.
 */

@Controller
@RequestMapping(value = "shift")
public class ShiftController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("shifts", ShiftData.getAll());
        return "shift/index";
    }

    @RequestMapping(value = "time-entry", method = RequestMethod.GET)
    public String displayTimeEntry(Model model) {
        model.addAttribute(new Shift());
        model.addAttribute("companies", CompanyData.getAll());
        return "shift/time-entry";
    }

    @RequestMapping(value = "time-entry", method = RequestMethod.POST)
    public String processTimeEntry(Model model, @ModelAttribute @Valid Shift shift, int companyId) {
        Company comp = CompanyData.getById(companyId);
        shift.setCompany(comp);
        ShiftData.add(shift);
        return "shift/confirmation";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveForm(Model model) {
        model.addAttribute("shifts", ShiftData.getAll());
        return "shift/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveForm(@RequestParam int[] shiftIds) {
        for (int shiftId : shiftIds) {
            ShiftData.remove(shiftId);
        }
        return "redirect:";
    }

    @RequestMapping(value = "edit/{shiftId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int shiftId) {
        Shift theShift = ShiftData.getById(shiftId);
        model.addAttribute("shift", theShift);
        return "shift/edit";
    }

    @RequestMapping(value = "edit/{shiftId}", method = RequestMethod.POST)
    public String processEditForm(@PathVariable int shiftId, String clockedIn, String clockedOut) {
        Shift theShift = ShiftData.getById(shiftId);
        theShift.setClockedIn(clockedIn);
        theShift.setClockedOut(clockedOut);
        return "redirect:/shift/";

    }

}