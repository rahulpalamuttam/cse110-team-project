package com.googleit.telecom.models.users;

import com.googleit.telecom.models.items.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class SalesRep extends User {

    public SalesRep(String email, String password, String role) {
        super(email, password, role);
    }

    public void CreateService(String serviceName,
                              String serviceDescription, Date start,
                              Date end, Double price, Long id) {}

    public void CreatePackage(List<Service> services) {}

}
