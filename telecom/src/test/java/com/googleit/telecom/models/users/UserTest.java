package com.googleit.telecom.models.users;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
    private User user;
    
    @Before
    public void setUp() throws Exception {
        this.user = new User();
    }

    @Test
    public void testGetId() {
        // By default id should be 0
        assertEquals(0, 0);
        
        // Set id then test getId
        for(int i=0; i<50; i++) {
            user.setId(i);
            assertEquals(i, user.getId());
        }
    }

    @Test
    public void testSetId() {
        // Set id then test with getID
        for(int i=0; i<50; i++) {
            user.setId(i);
            assertEquals(user.getId(), i);
        }
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
    public void testSetEmail() {
    	String testMail = "test3456@ucsd.edu";
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
    public void testSetPassword() {
       String testPassword = "testpassword";
          
       // set email then test setEmail
       user.setPassword(testPassword);
       assertEquals(user.getPassword(), testPassword);    	
    }


}
