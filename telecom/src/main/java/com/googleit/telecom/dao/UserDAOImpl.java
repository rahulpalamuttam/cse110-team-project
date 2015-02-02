package com.googleit.telecom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.googleit.telecom.models.User;

public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    public UserDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void insert(final User user) {

        // Set current time having "yyy-MM-dd" format
        user.setReg_date(new SimpleDateFormat("yyy-MM-dd").format(new Date()));

        final String sql = "INSERT INTO users (email, password, last_name, first_name, reg_date, enabled)"
                + " VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getLast_name());
                ps.setString(4, user.getFirst_name());
                ps.setString(5, user.getReg_date());
                ps.setBoolean(6, true);
                return ps;
            }
        }, keyHolder);

        // Set primary key id to user
        user.setId(keyHolder.getKey().intValue());
        
        String sql1 = "INSERT INTO user_roles (email, role)" + " VALUES (?,?)";
        this.jdbcTemplate.update(sql1, user.getEmail(), "ROLE_USER");
    }
}
