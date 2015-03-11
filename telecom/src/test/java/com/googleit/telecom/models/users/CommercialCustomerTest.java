package com.googleit.telecom.models.users;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class CommercialCustomerTest {

   CommercialCustomer company;
    Customer cust;
    List<Customer> clist;
    @Before
    public void setup(){
        cust = new Customer();
        company = new CommercialCustomer(cust);
    }

    @Test
    public void testGetCompanyName() throws Exception {
        String testName = "VISA!";
        company.setCompanyName("VISA!");
        assertEquals(testName, company.getCompanyName());
    }

    @Test
    public void testAddCustomer() throws Exception {
        company.addCustomer(cust);
        clist = company.getcustomers();
        assertTrue(clist.contains(cust));
    }

    @Test
    public void testRemoveCustomer() throws Exception {
        company.addCustomer(cust);
        company.removeCustomer(cust);
        assertTrue(company.isPartofCompany(cust));
    }

    @Test
    public void testIsPartofCompany() throws Exception {
        assertTrue(company.isPartofCompany(cust));
    }
}