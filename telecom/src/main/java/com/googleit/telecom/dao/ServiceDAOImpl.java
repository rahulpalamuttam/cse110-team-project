package com.googleit.telecom.dao;

import com.googleit.telecom.models.items.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Joon on 2/10/2015.
 */
public class ServiceDAOImpl implements ServiceDAO {
    private JdbcTemplate jdbcTemplate;

    public ServiceDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Service> getUnsubscribedService(int user_id) {
        String sql = "SELECT * FROM services";

        return null;
    }

    @Override
    public List<Service> getSubscribedService(int user_id) {
        return null;
    }
}
