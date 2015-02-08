package com.googleit.telecom.models.users;

/**
 * Created by rahul on 2/1/15.
 */
public class CommercialCustomer extends Customer{
    private String CompanyName;

    public void setCompanyName(String name){
        this.CompanyName = name;
    }

    public String getCompanyName(){
        return this.CompanyName;
    }
}
