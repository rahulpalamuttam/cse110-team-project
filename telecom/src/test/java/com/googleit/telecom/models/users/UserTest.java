package com.googleit.telecom.models.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

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
        // By default email should be null
        assertEquals(null, user.getEmail());
        
        // 
    }

    @Test
    public void testSetEmail() {
    }

    @Test
    public void testGetPassword() {
    }

    @Test
    public void testSetPassword() {
    }

    @Test
    public void testGetLast_name() {
    }

    @Test
    public void testSetLast_name() {
    }

    @Test
    public void testGetFirst_name() {
    }

    @Test
    public void testSetFirst_name() {
    }

    @Test
    public void testGetReg_date() {
    }

    @Test
    public void testSetReg_date() {
    }


    @Test
    public void testChangeName() throws Exception {

    }

    @Test
    public void testRequestDelete() throws Exception {

    }

    @Test
    public void testChangePassword() throws Exception {

    }
}
