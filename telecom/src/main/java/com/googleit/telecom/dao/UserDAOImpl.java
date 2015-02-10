package com.googleit.telecom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.sql.DataSource;

import com.googleit.telecom.models.users.Customer;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.googleit.telecom.models.users.User;

public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    public UserDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void insert(final User user) {

        // Encrypte password
        BCryptPasswordEncoder passEncryp = new BCryptPasswordEncoder();
        user.setPassword(passEncryp.encode(user.getPassword()));
        
        // Set current time having "yyy-MM-dd" format
        user.setReg_date(new SimpleDateFormat("yyy-MM-dd").format(new Date()));

        final String sql1 = "INSERT INTO users (email, password, reg_date, enabled)"
                + " VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql1, new String[] { "id" });
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getReg_date());
                ps.setBoolean(4, true);
                return ps;
            }
        }, keyHolder);

        // Set primary key id to user
        user.setId(keyHolder.getKey().intValue());

        String sql2 = "INSERT INTO user_roles (email, role)" + " VALUES (?,?)";
        this.jdbcTemplate.update(sql2, user.getEmail(), "ROLE_USER");
    }

    @Override
    public User getUser(String email){
        String sql = "SELECT id, email, reg_date FROM users WHERE email=?";
        Map queried = new HashMap();
        try {
            queried = this.jdbcTemplate.queryForMap(sql, new Object[]{email});
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        Customer cust = new Customer();
        cust.setId((long)queried.get("id"));
        cust.setEmail((String) queried.get("email"));
        cust.setReg_date(queried.get("reg_date").toString());

        return cust;
    }

    /**
     * Currently this doesn't exactly check for duplicates.
     * All it does is check for
     * @param email
     * @return
     */
    @Override
    public boolean isDuplicate(String email) {
        String sql = "SELECT email FROM users WHERE email=?";

        try {
            this.jdbcTemplate.queryForObject(sql, new Object[] {email}, String.class);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }

        return true;
    }
}
