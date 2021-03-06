package com.googleit.telecom.models.users;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RetailCustomerTest {

    RetailCustomer oneperson;
    @Before
    public void IndividualCustomerTestSetup() throws Exception {
        oneperson = new RetailCustomer();
    }
    @Test
    public void testGetFirstName() throws Exception {
        String firstName = "Albert";
        oneperson.setFirstName("Albert");
        assertEquals(firstName, oneperson.getFirstName());
    }


    @Test
    public void testGetLastName() throws Exception {
        String lastName = "Lawrence";
        oneperson.setLastName("Lawrence");
        assertEquals(lastName, oneperson.getLastName());
    }
}