package com.justinluke.webtimesheet.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by there on 8/3/2017.
 */
public class CompanyData {

    static List<Company> companies = new ArrayList<>();

    public static List<Company> getAll() {
    return companies;
    }

    public static void add(Company newCompany) {
        companies.add(newCompany);
    }

    public static void remove(int id) {

    }

    public static Company getById(int id) {
        Company theCompany = null;
        for (Company candidateCompany : companies) {
            if (candidateCompany.getCompanyId()== id) {
                theCompany = candidateCompany;
            }
        }
        return theCompany;
    }
}
