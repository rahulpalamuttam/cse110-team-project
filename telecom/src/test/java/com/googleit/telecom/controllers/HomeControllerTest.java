package com.googleit.telecom.controllers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HomeControllerTest {

    HomeController homeController;

    @Before
    public void setup() {
        homeController = new HomeController();
    }

    @Test
    public void testHome() throws Exception {
        String home = homeController.home();
        assertEquals(home, "home");
    }
}