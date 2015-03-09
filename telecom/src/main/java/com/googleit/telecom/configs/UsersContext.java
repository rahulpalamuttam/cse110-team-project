package com.googleit.telecom.configs;

import com.googleit.telecom.dao.UserDAO;
import com.googleit.telecom.dao.UserRepository;
import com.googleit.telecom.models.users.Customer;
import com.googleit.telecom.models.users.CustomerRepresentative;
import com.googleit.telecom.models.users.SalesRep;
import com.googleit.telecom.models.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class UsersContext {
    private static final Logger logger = LoggerFactory.getLogger(UsersContext.class);

    @Bean
    public UserDAO userDAO() {
        logger.info("UserRepository Bean");
        UserDAO userDAO = new UserRepository();

        /* Sample Users */

        // Customer
        // Email : customer, Password : c
        User user = new Customer("cus", "c", "ROLE_CUSTOMER");
        userDAO.register(user);

        logger.info("Added Customer: Email => {}, Password => {}", user.getEmail(), user.getPassword());


        // Customer Rep
        // Email : crep, Password : c
        user = new CustomerRepresentative("crep", "c", "ROLE_CUSTOMER_REP");
        user.setRole("ROLE_CUSTOMER_REP");
        userDAO.register(user);

        logger.info("Added Customer Rep: Email => {}, Password => {}", user.getEmail(), user.getPassword());

        // Marketing Rep
        // Email : mrep, Password : m
        user = new SalesRep("mrep", "m", "ROLE_MARK_REP");
        userDAO.register(user);

        logger.info("Added Marketing Rep: Email => {}, Password => {}", user.getEmail(), user.getPassword());

        logger.info("UserRepository bean configuration done");

        return userDAO;
    }

    //public UsersContext() {
    //    logger.info("UsersContext() constructor");
    //
    //    /* Sample Users */
    //
    //    // Customer
    //    // Email : customer, Password : c
    //    User user = new Customer("cus", "c", LocalDate.now());
    //    users.add(user);
    //
    //    logger.info("Added Customer: Email => {}, Password => {}", user.getEmail(), user.getPassword());
    //
    //
    //    // Customer Rep
    //    // Email : crep, Password : c
    //    user = new CustomerRepresentative("crep", "c", LocalDate.now());
    //    users.add(user);
    //
    //    logger.info("Added Customer Rep: Email => {}, Password => {}", user.getEmail(), user.getPassword());
    //
    //    // Marketing Rep
    //    // Email : mrep, Password : m
    //    user = new SalesRep("mrep", "m", LocalDate.now());
    //    users.add(user);
    //
    //    logger.info("Added Marketing Rep: Email => {}, Password => {}", user.getEmail(), user.getPassword());
    //
    //    logger.info("Adding user list into UserRepository");
    //    //for (User u : users)
    //    //    userDAO.register(u);
    //
    //    logger.info("UserContext configuration done");
    //}
}
