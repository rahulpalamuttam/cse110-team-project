package com.googleit.telecom.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class AuthDAOImpl implements AuthDAO {
    private JdbcTemplate jdbcTemplate;
    
    public AuthDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public boolean authenticate(String email, String password) {
        String sql;
        List result;
        System.out.println(email +" ::  " + password);
        if(!email.isEmpty() && !password.isEmpty()) {
            sql = "SELECT email FROM users WHERE email='" + email + "' AND password='" + password + "'";
            result = jdbcTemplate.queryForList(sql);
            
            return (result.size() > 0);
        }
        
        return false;
    }
}
