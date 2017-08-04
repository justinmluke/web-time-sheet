package com.justinluke.webtimesheet.controllers;

import com.justinluke.webtimesheet.models.CompanyData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
