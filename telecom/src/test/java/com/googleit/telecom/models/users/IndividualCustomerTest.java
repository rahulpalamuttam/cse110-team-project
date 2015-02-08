package com.googleit.telecom.models.users;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class IndividualCustomerTest {

    IndividualCustomer oneperson;
    @Before
    public void IndividualCustomerTestSetup() throws Exception {
        oneperson = new IndividualCustomer();
    }
    @Test
    public void testGetFirstName() throws Exception {
        String firstName = "Albert";
        oneperson.setFirstName("Albert");
        assertEquals(firstName, oneperson.getFirstName());
    }


    @Test
    public void testGetLastName() throws Exception {

    }
}