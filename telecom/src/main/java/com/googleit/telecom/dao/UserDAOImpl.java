package com.googleit.telecom.dao;

import com.googleit.telecom.Notifier.Bill;
import com.googleit.telecom.models.users.Customer;
import com.googleit.telecom.models.users.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        this.jdbcTemplate.update(sql2, user.getEmail(), "ROLE_CUSTOMER");
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
        cust.setId(Long.valueOf((Long) queried.get("id")));
        cust.setEmail((String) queried.get("email"));
        cust.setReg_date(queried.get("reg_date").toString());
        cust.setCustomerBill(new Bill());
        return cust;
    }

    @Override
    public void setThreshold(long id, Double threshold){
        String sql = "UPDATE users SET threshold=" + threshold +" WHERE id=" + id;
        try {
            this.jdbcTemplate.execute(sql);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }
    @Override
    public User getUser(long id){
        String sql = "SELECT id, email, reg_date, threshold FROM users WHERE id=?";
        Map queried = new HashMap();
        try {
            queried = this.jdbcTemplate.queryForMap(sql, new Object[]{id});
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        Customer cust = new Customer();
        Long len = (Long)queried.get("id");
        System.out.println(len);
        cust.setId((long)queried.get("id"));
        cust.setEmail((String) queried.get("email"));
        cust.setReg_date(queried.get("reg_date").toString());
        double threshold = (double) queried.get("threshold");
        Bill bill = cust.getCustomerBill();
        bill.setThreshold(threshold);
        bill.addObserver(cust);
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
