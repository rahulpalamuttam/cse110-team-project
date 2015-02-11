package com.googleit.telecom.dao;

import com.googleit.telecom.models.users.Customer;

import java.util.List;

/**
 * Created by Joon on 2/11/2015.
 */
public class CUStomerDAOImpl implements  CustomerDAO {

    @Override
    public List<Customer> getCustomers(long customer_rep_id) {
        // TODO :: get customers under specific customer rep
        return null;
    }
}
