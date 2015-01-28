package com.googleit.telecom.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.googleit.telecom.models.User;


public class UserDAOImpl implements UserDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public UserDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void insert(User user) {
		String sql = "INSERT INTO users (email, password, last_name, first_name, reg_date)"
	            + " VALUES (?, ?, ?, ?, ?)";
		this.jdbcTemplate.update(sql, user.getEmail(), user.getPassword(),
				user.getLast_name(), user.getFirst_name(), user.getReg_date());
	}
}
