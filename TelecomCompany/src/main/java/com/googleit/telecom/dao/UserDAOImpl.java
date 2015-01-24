package com.googleit.telecom.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.googleit.telecom.models.User;


public class UserDAOImpl implements UserDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public UserDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void insert(User user) {
		String sql = "INSERT INTO user (email, password, last_name, first_name, reg_date)"
	            + " VALUES (?, ?, ?, ?)";
	jdbcTemplate.update(sql,);
		
	}

}
