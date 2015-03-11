package com.googleit.telecom.configs;

import com.googleit.telecom.dao.UserDAO;
import com.googleit.telecom.dao.UserRepository;
import com.googleit.telecom.models.users.Customer;
import com.googleit.telecom.models.users.CustomerRepresentative;
import com.googleit.telecom.models.users.MarketingRep;
import com.googleit.telecom.models.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserContext {
    private static final Logger logger = LoggerFactory.getLogger(UserContext.class);

    @Bean
    public UserDAO userDAO() {
        logger.info("UserRepository Bean");
        UserDAO userDAO = new UserRepository();

        /* Sample Users */

        // Customer
        // Email : customer, Password : c
        Customer cus = new Customer("cus", "c", "ROLE_CUSTOMER");
        cus.subscribeService((long) 1);
        cus.subscribeService((long) 2);
        cus.subscribeService((long) 3);
        userDAO.register(cus);

        logger.info("Added Customer: Email => {}, Password => {}", cus.getEmail(), cus.getPassword());


        // Customer Rep
        // Email : crep, Password : c
        CustomerRepresentative crep = new CustomerRepresentative("crep", "c", "ROLE_CUSTOMER_REP");
        userDAO.register(crep);

        logger.info("Added Customer Rep: Email => {}, Password => {}", crep.getEmail(), crep.getPassword());

        // Marketing Rep
        // Email : mrep, Password : m
        MarketingRep mrep = new MarketingRep("mrep", "m", "ROLE_MARK_REP");
        userDAO.register(mrep);

        logger.info("Added Marketing Rep: Email => {}, Password => {}", mrep.getEmail(), mrep.getPassword());

        logger.info("UserRepository bean configuration done");

        return userDAO;
    }
}
