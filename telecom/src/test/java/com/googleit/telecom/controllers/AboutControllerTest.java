package com.googleit.telecom.controllers;


import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.*;

public class AboutControllerTest {

    AboutController aboutController;
    @Before
    public void setup(){
        aboutController = new AboutController();
    }
    @Test
    public void testLogin() throws Exception {
        String test = aboutController.login(new Model() {
            @Override
            public Model addAttribute(String attributeName, Object attributeValue) {
                return null;
            }

            @Override
            public Model addAttribute(Object attributeValue) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> attributeValues) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public boolean containsAttribute(String attributeName) {
                return false;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        });

        assertEquals(test, "pages/about");

    }
}