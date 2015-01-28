package com.googleit.telecom.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class AuthDAOImpl implements AuthDAO {
    private JdbcTemplate jdbcTemplate;
    
    public AuthDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public boolean authenticate(String email, String password) {
        
//        String sql = "SELECT email FROM users WHERE email=? AND password=?";
        
        
        
        return false;
    }
}
