package com.justinluke.webtimesheet.controllers;

import com.justinluke.webtimesheet.models.Shift;
import com.justinluke.webtimesheet.models.ShiftData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String timeEntry(Model model) {
        model.addAttribute(new Shift());
        return "shift/time-entry";
    }

    @RequestMapping(value = "time-entry", method = RequestMethod.POST)
    public String timeEntry(Model model, @ModelAttribute @Valid Shift shift) {
        ShiftData.add(shift);
        return "shift/confirmation";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String remove(Model model) {
        model.addAttribute("shifts", ShiftData.getAll());
        return "shift/remove";
    }

}
