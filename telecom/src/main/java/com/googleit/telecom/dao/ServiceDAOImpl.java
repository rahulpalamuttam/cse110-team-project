package com.googleit.telecom.dao;

import com.googleit.telecom.Factories.BuyableFactory;
import com.googleit.telecom.models.items.BuyableType;
import com.googleit.telecom.models.items.Service;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceDAOImpl implements ServiceDAO {
    private JdbcTemplate jdbcTemplate;
    private BuyableFactory buyableFactory;
    public ServiceDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.buyableFactory = new BuyableFactory();
    }

    @Override
    public List<Service> getUnsubscribedService(long user_id) {
        // No join necessary here

        String sql = "SELECT service_id, service_name, price, start_date, end_date, service_description, duration FROM services "  +
                "WHERE NOT EXISTS (" +
                "SELECT * FROM service_subscriptions WHERE service_subscriptions.service_id=services.service_id AND service_subscriptions.customer_id=?" +
                " )";

        List<Map<String,Object>> queried = new ArrayList<>();
        try {
            queried = this.jdbcTemplate.queryForList(sql, new Object[]{user_id});
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return (List<Service>)(List<?>)buyableFactory.getBuyableList(queried, BuyableType.SERVICE_TYPE);
    }

    @Override
    public List<Service> getSubscribedService(long user_id) {
        String sql = "SELECT service_id, service_name, price, start_date, end_date, service_description, duration FROM services "  +
                "WHERE EXISTS(" +
                "SELECT * FROM service_subscriptions WHERE service_subscriptions.service_id=services.service_id AND service_subscriptions.customer_id=?" +
                " )";

        List<Map<String,Object>> queried = new ArrayList<>();
        try {
            queried = this.jdbcTemplate.queryForList(sql, new Object[]{user_id});
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return (List<Service>)(List<?>)buyableFactory.getBuyableList(queried, BuyableType.SERVICE_TYPE);
    }

    @Override
    public void addService(long service_id, long user_id) {
        String sql = "INSERT INTO service_subscriptions (service_id, customer_id) VALUES (?,?)";

        this.jdbcTemplate.update(sql, service_id, user_id);
    }

    @Override
    public void unsubscribeService(long service_id, long user_id) {
        String sql = "DELETE FROM service_subscriptions WHERE service_id=? AND customer_id=?";
        this.jdbcTemplate.update(sql, service_id, user_id);
    }

	@Override
	public List<Service> getAllService() {
        String sql = "SELECT service_id, service_name, service_description, price, start_date, end_date, duration FROM services";

        List<Map<String,Object>> queried = new ArrayList<>();
        try {
            queried = this.jdbcTemplate.queryForList(sql);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return (List<Service>)(List<?>)buyableFactory.getBuyableList(queried, BuyableType.SERVICE_TYPE);
	}

	@Override
	public void createService(Service service) {
		// TODO Auto-generated method stub
        String sql2 = "INSERT INTO services (service_name, service_description, price, duration)" + " VALUES (?,?,?,?)";
        this.jdbcTemplate.update(sql2, service.getServiceName(), service.getServiceDescription(), service.getPrice(), service.getDuration());

		
	}
}
