package com.googleit.telecom.models.users;

import java.util.List;

/**
 * Created by rahul on 2/1/15.
 */
public class CommercialCustomer extends Customer{
    private String CompanyName;
    private List<Customer> customers;
    public void setCompanyName(String name){
        this.CompanyName = name;
    }

    public String getCompanyName(){
        return this.CompanyName;
    }

    public void addCustomer(Customer cust){
    }

    public void removeCustomer(Customer cust){
    }

    public boolean isPartofCompany(Customer cust){
        return customers.contains(cust);
    }


}
