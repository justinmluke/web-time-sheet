package com.justinluke.webtimesheet.controllers;

import com.justinluke.webtimesheet.models.Company;
import com.justinluke.webtimesheet.models.Shift;
import com.justinluke.webtimesheet.models.data.CompanyDao;
import com.justinluke.webtimesheet.models.data.ShiftDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by there on 7/15/2017.
 */

@Controller
@RequestMapping(value = "shift")
public class ShiftController {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private ShiftDao shiftDao;

    @RequestMapping(value = "time-entry", method = RequestMethod.GET)
    public String displayTimeEntry(Model model) {
        model.addAttribute(new Shift());
        model.addAttribute("companies", companyDao.findAll());
        return "shift/time-entry";
    }

    @RequestMapping(value = "time-entry", method = RequestMethod.POST)
    public String processTimeEntry(Model model, @ModelAttribute @Valid Shift shift, Errors errors, @RequestParam int companyId) {
        Company comp = companyDao.findOne(companyId);
        shift.setCompany(comp);
        comp.addShift(shift);
        shiftDao.save(shift);
        return "shift/confirmation";
    }

}