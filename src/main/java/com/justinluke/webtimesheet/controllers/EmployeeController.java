package com.justinluke.webtimesheet.controllers;

import com.justinluke.webtimesheet.models.CompanyData;
import com.justinluke.webtimesheet.models.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by there on 8/19/2017.
 */

@Controller
@RequestMapping(value = "")
public class EmployeeController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("companies", CompanyData.getAll());
        return "employee/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Employee());
        return "employee/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Employee employee, Errors errors) {

        model.addAttribute(employee);
        boolean passwordsMatch = true;
        if (employee.getPassword() == null || employee.getVerifyPassword() == null
                || !employee.checkPassword(employee.getPassword())) {
            passwordsMatch = false;
            employee.setPassword("");
        }
        if (!errors.hasErrors() && passwordsMatch) {
            return "employee/index";
        }
        return "employee/add";
    }

}
