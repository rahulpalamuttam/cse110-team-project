package com.googleit.telecom.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.*;

public class ServicesControllerTest {

    ServicesController servicesController;
    Model model;
    @Before
    public void Setup() {
        servicesController = new ServicesController();
        model = new Model() {
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
        };
    }

    @Test
    public void testLogin() throws Exception {
        String login = servicesController.login(model);
        assertEquals(login, "pages/services");
    }
}