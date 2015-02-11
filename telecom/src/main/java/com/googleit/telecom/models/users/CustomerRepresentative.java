package com.googleit.telecom.models.users;

import java.util.List;

/**
 * Created by rahul on 2/10/15.
 */
public class CustomerRepresentative extends User {
    private List<Customer> customerList;
    private String address;

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customer getCustomer(long id){
        Customer newCustomer = new Customer();
        for(Customer customer : customerList){
            if(customer.getId() == (id)) return customer;
        }
        return null;
    }

    public void addCustomer(Customer customer){
        customerList.add(customer);
    }
}
