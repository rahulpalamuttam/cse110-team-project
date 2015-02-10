package com.googleit.telecom.dao;

import com.googleit.telecom.models.items.Service;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.*;

/**
 * Created by Joon on 2/10/2015.
 */
public class ServiceDAOImpl implements ServiceDAO {
    private JdbcTemplate jdbcTemplate;

    public ServiceDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Service> getUnsubscribedService(long user_id) {
        // No join necessary here

        String sql = "SELECT services.service_id AS id, service_name, price, start_date, end_date, service_description FROM services "  +
                "WHERE NOT EXISTS (" +
                "SELECT * FROM subscriptions WHERE subscriptions.service_id=services.service_id AND subscriptions.customer_id=?" +
                " )";

        List<Map<String,Object>> queried = new ArrayList<>();
        try {
            queried = this.jdbcTemplate.queryForList(sql, new Object[]{user_id});
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return MapServicesToList(queried);
    }

    @Override
    public List<Service> getSubscribedService(long user_id) {
        String sql = "SELECT services.service_id AS id, service_name, price, start_date, end_date, service_description FROM services "  +
                "WHERE EXISTS(" +
                "SELECT * FROM subscriptions WHERE subscriptions.service_id=services.service_id AND subscriptions.customer_id=?" +
                " )";

        List<Map<String,Object>> queried = new ArrayList<>();
        try {
            queried = this.jdbcTemplate.queryForList(sql, new Object[]{user_id});
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return MapServicesToList(queried);
    }

    /**
     * Extracts the data from a SQL query and encapsulates it within
     * a List of Service Objects.
     * @param tuples
     * @return
     */
    public List<Service> MapServicesToList(List<Map<String,Object>> tuples){
        List<Service> services = new ArrayList<>();
        for(Map<String,Object> tuple : tuples){
            Service service = new Service();
            service.setServiceID((Long)tuple.get("id"));
            service.setServiceName((String) tuple.get("service_name"));
            service.setDuration((Date)tuple.get("start_date"), (Date)tuple.get("end_date"));
            service.setPrice((Double)tuple.get("price"));
            service.setServiceDescription((String)tuple.get("service_description"));

            services.add(service);
        }
        // If there's better solution use that statement
        return services;
    }
}
