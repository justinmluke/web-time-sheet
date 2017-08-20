package com.justinluke.webtimesheet.controllers;

import com.justinluke.webtimesheet.models.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by there on 8/19/2017.
 */

@Controller
@RequestMapping(value = "")
public class EmployeeController {

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Employee());
        return "employee/add";
    }
}
