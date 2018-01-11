package com.justinluke.webtimesheet.controllers;

import com.justinluke.webtimesheet.models.Company;
import com.justinluke.webtimesheet.models.Shift;
import com.justinluke.webtimesheet.models.User;
import com.justinluke.webtimesheet.models.data.CompanyDao;
import com.justinluke.webtimesheet.models.data.ShiftDao;
import com.justinluke.webtimesheet.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by there on 8/3/2017.
 */
@Controller
@RequestMapping(value = "company")
public class CompanyController extends AuthenticationController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private ShiftDao shiftDao;


    @RequestMapping(value = "view/{companyId}", method = RequestMethod.GET)
    public String displayCompany(Model model, @PathVariable int companyId, HttpServletRequest request){
    Company comp = companyDao.findOne(companyId);
    User user = getUserForModel(request);
    List<Shift> theShifts = user.findShiftsbyCompany(comp);
    model.addAttribute("shifts", theShifts);
    model.addAttribute("company", comp);
        return "company/view";
    }

    @RequestMapping(value = "add-shift/{uid}", method = RequestMethod.GET)
    public String displayAddShift(Model model, @PathVariable int uid) {
        User theUser = userDao.findOne(uid);
        model.addAttribute("companies", companyDao.findByUser(theUser));

        return "company/add-shift";
    }

    @RequestMapping(value = "add-shift/{uid}", method = RequestMethod.POST)
    public String processAddShift(Model model, @PathVariable int uid, @RequestParam("shiftDate") String shiftDate,
                                  @RequestParam("shiftTimeIn") String shiftTimeIn, @RequestParam("shiftTimeOut") String shiftTimeOut,
                                  @RequestParam("companyId") int companyId) {

        User theUser = userDao.findOne(uid);
        Company theCompany = companyDao.findOne(companyId);
        Shift addedShift = new Shift(shiftDate, shiftTimeIn, shiftTimeOut, theCompany, theUser);
        theCompany.getShifts().add(addedShift);
        theUser.addShift(addedShift);
        shiftDao.save(addedShift);

        return "redirect:/company/view/" + companyId;


        }


}

