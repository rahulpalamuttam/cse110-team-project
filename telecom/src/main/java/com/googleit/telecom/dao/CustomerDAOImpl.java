package com.googleit.telecom.dao;

import com.googleit.telecom.models.users.Customer;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Joon on 2/11/2015.
 */
public class CustomerDAOImpl implements  CustomerDAO {
    private JdbcTemplate jdbcTemplate;

    public CustomerDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Customer> getCustomers(long customer_rep_id) {
        // TODO :: get customers under specific customer rep
        return null;
    }
}
