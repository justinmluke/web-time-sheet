package com.justinluke.webtimesheet.controllers;

import com.justinluke.webtimesheet.models.Company;
import com.justinluke.webtimesheet.models.User;
import com.justinluke.webtimesheet.models.data.CompanyDao;
import com.justinluke.webtimesheet.models.data.UserDao;
import com.justinluke.webtimesheet.models.forms.AddCompanyForm;
import com.justinluke.webtimesheet.models.forms.LoginForm;
import com.justinluke.webtimesheet.models.forms.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by there on 8/22/2017.
 */
@Controller
public class AuthenticationController extends AbstractController{

    @Autowired
    private UserDao userDao;

    @Autowired
    private CompanyDao companyDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request){
        User user = getUserForModel(request);
        model.addAttribute("title", "My Employers");
        model.addAttribute("companies", companyDao.findByUser(user));
        model.addAttribute("user", (user));
        return "user/index";
    }

    @RequestMapping(value = "add-company/{id}", method = RequestMethod.GET)
    public String displayAddCompany(@PathVariable int id, Model model) {
        User user = userDao.findOne(id);
        model.addAttribute("title", "Add Employer");
        model.addAttribute("user", user);
        AddCompanyForm form = new AddCompanyForm(user, companyDao.findAll());
        model.addAttribute("form", form);

        return "user/add-company";
    }

    @RequestMapping(value = "add-company/{id}", method = RequestMethod.POST)
    public String processAddCompany(@PathVariable int id, Model model,
                             @ModelAttribute @Valid AddCompanyForm form, Errors errors, int companyId) {

        Company addedCompany = companyDao.findOne(companyId);
        User theUser = userDao.findOne(id);
        List<Company> myCompanies = theUser.getCompanies();

        if (errors.hasErrors()) {
            return "redirect:/add-company/" + theUser.getUid();
        }

        if (myCompanies.contains(addedCompany)) {
            model.addAttribute("error", "You already work for this company. " +
                    "Please select another employer.");
            return "redirect:/add-company/" + theUser.getUid();
        }

        theUser.addCompany(addedCompany);
        userDao.save(theUser);

        return "redirect:/";

    }

    @RequestMapping(value = "remove-company/{uid}", method = RequestMethod.GET)
    public String displayRemoveCompany(@PathVariable int uid, Model model) {
        model.addAttribute("title", "Remove Employer");
        User user = userDao.findOne(uid);
        model.addAttribute("user", user);

        return "user/remove-company";
    }

    @RequestMapping(value = "remove-company/{uid}", method = RequestMethod.POST)
    public String processRemoveCompany(@PathVariable int uid, @RequestParam(value = "companyId") int id) {
        User theUser = userDao.findOne(uid);
        List<Company> myCompanies = theUser.getCompanies();
        Company theCompany = companyDao.findOne(id);

        if (myCompanies.contains(theCompany)) {
            myCompanies.remove(theCompany);
        }

        userDao.save(theUser);

        return "redirect:/";
    }

    @RequestMapping(value = "submit-company", method = RequestMethod.GET)
    public String displaySubmitCompany(Model model, HttpServletRequest request){
        model.addAttribute("title", "Submit New Employer");
        User theUser = getUserForModel(request);
        model.addAttribute("user", theUser);

        return "user/submit-company";
    }

    @RequestMapping(value = "submit-company", method = RequestMethod.POST)
    public String processSubmitCompany(Model model, @RequestParam("companyName") String companyName,
                                       @RequestParam("userId") int userId){
        if (companyName == ""){
            model.addAttribute("error", "Please enter a valid name");
            return "user/submit-company";
        }

        Company newCompany = new Company(companyName);
        companyDao.save(newCompany);
        return "redirect:/add-company/" + userId;
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String displayAllShifts(Model model, HttpServletRequest request) {
        User theUser = getUserForModel(request);
        Iterable<Company> myCompanies = companyDao.findByUser(theUser);
        model.addAttribute("title", "My Employers");
        model.addAttribute("companies", myCompanies);

        return "user/all-shifts";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute(new RegisterForm());
        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute @Valid RegisterForm form, Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) {
            return "user/register";
        }

        User existingUser = userDao.findByEmail(form.getEmail());

        if (existingUser != null) {
            errors.rejectValue("email", "email.alreadyexists", "A user with that email already exists.");
            return "user/register";
        }

        User newUser = new User(form.getEmail(), form.getPassword());
        userDao.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return "redirect:";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute(new LoginForm());
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute @Valid LoginForm form, Errors errors, HttpServletRequest request){

        if (errors.hasErrors()) {
            return "user/login";
        }

        User theUser = userDao.findByEmail(form.getEmail());
        String password = form.getPassword();

        if (theUser == null) {
            errors.rejectValue("email", "email.invalid", "The given email does not exist");
            return "user/login";
        }

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid Password");
            return "user/login";
        }

        setUserInSession(request.getSession(), theUser);

        return "redirect:";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

}
