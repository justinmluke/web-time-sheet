package com.justinluke.webtimesheet.controllers;

import com.justinluke.webtimesheet.models.Company;
import com.justinluke.webtimesheet.models.CompanyData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.Locale;


/**
 * Created by there on 8/3/2017.
 */
@Controller
@RequestMapping(value = "company")
public class CompanyController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("companies", CompanyData.getAll());
        return "company/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Company());
        return "company/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@ModelAttribute @Valid Company company, Errors errors) {
        if (errors.hasErrors()){
            return "company/add";
        }
        CompanyData.add(company);
        return "redirect:/company/";
    }

    @RequestMapping(value = "view/{companyId}", method = RequestMethod.GET)
    public String viewCompany(Model model, @PathVariable int companyId){
    Company comp = CompanyData.getById(companyId);
    model.addAttribute("company", comp);
        return "company/view";
    }
}
