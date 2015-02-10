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
        // TODO :: return list of Unsubscribed Service
        // For SQL statement try using JOIN
        /**
         * This is actually funding the disjunction of the two sets.
         * I don't know if a JOIN is necessary here.
         */

        // If there's better solution use that statement
        return null;
    }

    @Override
    public List<Service> getSubscribedService(long user_id) {
        // TODO :: return list of Unsubscribed Service
        // For SQL statement try using "join"
        String sql = "SELECT subscriptions.service_id AS id, service_name, price, start_date, end_date, service_description from subscriptions "  +
                "INNER JOIN services ON subscriptions.service_id=services.service_id " +
                "WHERE subscriptions.customer_id=?";

        List<Map<String,Object>> queried = new ArrayList<>();
        try {
            queried = this.jdbcTemplate.queryForList(sql, new Object[]{user_id});
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return MapServicesToList(queried);
    }

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
