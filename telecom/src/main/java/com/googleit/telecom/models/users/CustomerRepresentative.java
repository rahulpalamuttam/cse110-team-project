package com.googleit.telecom.models.users;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepresentative extends User {
    private List<Customer> customerList;

    public CustomerRepresentative(String email, String password, String role) {
        super(email, password, role);
        this.customerList = new ArrayList<>();
    }
}
