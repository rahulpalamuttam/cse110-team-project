package com.googleit.telecom.controllers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AuthControllerTest {

    AuthController authController;
    @Before
    public void Setup() {
        authController = new AuthController();
    }
    @Test
    public void testLogin() throws Exception {
        String login = authController.login();
        assertEquals(login, "auth/login");
    }

    @Test
    public void testLogout() throws Exception {
        String logout = authController.logout();
        assertEquals(logout, "auth/logout");
    }
}