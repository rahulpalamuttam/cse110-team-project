package com.googleit.telecom.models.users;
import com.googleit.telecom.models.*;
import java.util.List;
import java.util.ArrayList;
/**
 * Created by rahul on 2/1/15.
 */
public class CommercialCustomer extends Customer{
    private String CompanyName;
    private List<Customer> customers;

    public CommercialCustomer(Customer... customer){
        customers = new ArrayList<Customer>();
        for(Customer newcust : customer){
            customers.add(newcust);
        }
    }

    public void setCompanyName(String name){
        this.CompanyName = name;
    }

    public String getCompanyName(){
        return this.CompanyName;
    }

    public void addCustomer(Customer cust){
        customers.add(cust);
    }

    public void setcustomers(List<Customer> clist){
        this.customers = clist;
    }

    public void removeCustomer(Customer cust){
        int index;
        index = customers.indexOf(cust);
        customers.remove(index);

    }

    public List<Customer> getcustomers (){
        return customers;
    }

    public boolean isPartofCompany(Customer cust){
        return customers.contains(cust);
    }


}
