package com.justinluke.webtimesheet.controllers;

import com.justinluke.webtimesheet.models.Shift;
import com.justinluke.webtimesheet.models.ShiftData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "shift/time-entry";
    }

    @RequestMapping(value = "time-entry", method = RequestMethod.POST)
    public String processTimeEntry(Model model, @ModelAttribute @Valid Shift shift) {
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
}
