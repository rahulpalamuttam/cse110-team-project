package com.googleit.telecom.models.users;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

}