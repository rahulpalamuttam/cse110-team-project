package com.googleit.telecom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.dao.DuplicateKeyException;
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
    public boolean insert(final User user) {

        // Encrypte password
        BCryptPasswordEncoder passEncryp = new BCryptPasswordEncoder();
        user.setPassword(passEncryp.encode(user.getPassword()));
        
        // Set current time having "yyy-MM-dd" format
        user.setReg_date(new SimpleDateFormat("yyy-MM-dd").format(new Date()));

        final String sql = "INSERT INTO users (email, password, reg_date, enabled)"
                + " VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                    ps.setString(1, user.getEmail());
                    ps.setString(2, user.getPassword());
                    ps.setString(3, user.getReg_date());
                    ps.setBoolean(4, true);
                    return ps;
                }
            }, keyHolder);
        } catch (DuplicateKeyException dke) {
            return false;
        }

        // Set primary key id to user
        user.setId(keyHolder.getKey().intValue());
        
        String sql1 = "INSERT INTO user_roles (email, role)" + " VALUES (?,?)";
        this.jdbcTemplate.update(sql1, user.getEmail(), "ROLE_USER");
        
        return true;
    }
}
