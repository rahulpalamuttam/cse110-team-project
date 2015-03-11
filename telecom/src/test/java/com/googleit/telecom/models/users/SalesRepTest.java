package com.googleit.telecom.models.users;

import com.googleit.telecom.models.items.Service;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class SalesRepTest {

    SalesRep salesRep;
    @Before
    public void setup(){
        salesRep = new SalesRep();
    }

    @Test
    public void testCreateService() throws Exception {
        Date start = new Date();
        Date end = new Date();
        salesRep.CreateService("Good service", "Good", start, end, 29.00, (long) 12342);
    }

    @Test
    public void testCreatePackage() throws Exception {
        ArrayList<Service> listy = new ArrayList<>();
        salesRep.CreatePackage(listy);

    }
}