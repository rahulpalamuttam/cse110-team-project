package com.googleit.telecom.dao;

import com.googleit.telecom.models.users.Customer;

import java.util.List;

public interface CustomerDAO {
    public List<Customer> getCustomers(long customer_rep_id, ServiceDAO serviceDAO, packageDAO PackageDAO);
    public Customer getCustomer(String email, ServiceDAO serviceDAO, packageDAO PackageDAO);
    public Customer getCustomer(long user_id, ServiceDAO serviceDAO, packageDAO PackageDAO);
}
