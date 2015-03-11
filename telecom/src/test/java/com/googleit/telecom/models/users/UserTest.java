package com.googleit.telecom.models.users;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    private User user;
    
    @Before
    public void setUp() throws Exception {
        this.user = new User();
    }

    @Test
    public void testGetId() {
        // By default id should be 0

        // Set id then test getId

            user.setId((long) 12321321);
            assertEquals((long) 12321321, user.getId());
    }

    @Test
    public void testGetEmail() {
    	String testMail = "test123@ucsd.edu";
        // By default email should be null
        assertEquals(null, user.getEmail());
        
        // set email then test setEmail
        user.setEmail(testMail);
        assertEquals(user.getEmail(), testMail);
        	
    }

    @Test
    public void testGetPassword() {
       String testPassword = "testpassword";
       
   	   // set email then test setEmail
       user.setPassword(testPassword);
       assertEquals(user.getPassword(), testPassword);
    }
    
    @Test
    public void testgetReg_date() {
        String testDate = "February 19th";
    	   // set email then test setEmail
        user.setReg_date(testDate);
        assertEquals(user.getReg_date(), testDate);
     }

    @Test
    public void testtoString() {
        String testMail = "test123@ucsd.edu";
        user.setEmail(testMail);
        String testDate = "February 19th";
        user.setReg_date(testDate);
        String to = user.toString();
        assertEquals(to, "test123@ucsd.edu February 19th");
    }
}
