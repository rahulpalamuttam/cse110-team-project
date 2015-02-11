package com.googleit.telecom.dao;

import com.googleit.telecom.models.users.Customer;
import com.googleit.telecom.models.users.CustomerRepresentative;
import com.googleit.telecom.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Joon on 2/11/2015.
 */
public class CustomerDAOImpl implements  CustomerDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDAO userDAO;

    public CustomerDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Customer> getCustomers(long customer_rep_id) {
        // TODO :: get customers under specific customer rep

        String sql = "select email FROM users WHERE id IN (SELECT customer_id FROM customer_relations WHERE customer_rep_id=?)";
        List<String> newQueried = new ArrayList<>();
        List<Customer> customerList = new ArrayList<>();
        try {
            newQueried = this.jdbcTemplate.queryForList(sql, new Object[]{customer_rep_id}, String.class);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        for(String email : newQueried){
            customerList.add((Customer) userDAO.getUser(email));
        }

        System.out.println(customerList);
        return customerList;
    }
}
