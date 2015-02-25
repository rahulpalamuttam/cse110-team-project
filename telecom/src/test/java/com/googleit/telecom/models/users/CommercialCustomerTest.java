package com.googleit.telecom.models.users;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommercialCustomerTest {

    CommercialCustomer company;
    @Before
    public void CommercialCustomerTestSetup(){
        company = new CommercialCustomer();
    }

    @Test
    public void testGetCompanyName() throws Exception {
        String testName = "VISA!";
        company.setCompanyName("VISA!");
        assertEquals(testName, company.getCompanyName());
    }

    @Test
    public void testAddCustomer() throws Exception {
        Customer cust = new Customer();
        company.addCustomer(cust);
        assertTrue(company.isPartofCompany(cust));
    }

    @Test
    public void testRemoveCustomer() throws Exception {
        Customer cust = new Customer();
        company.addCustomer(cust);
        company.removeCustomer(cust);
        assertTrue(company.isPartofCompany(cust));
    }

    @Test
    public void testIsPartofCompany() throws Exception {
        Customer cust = new Customer();
        assertTrue(company.isPartofCompany(cust));
    }
}