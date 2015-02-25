package com.googleit.telecom.dao;

import com.googleit.telecom.models.items.Package;
import com.googleit.telecom.models.items.Service;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.*;

/**
 * Created by rahul on 2/24/15.
 */
public class packageDAOImpl implements packageDAO {
    private JdbcTemplate jdbcTemplate;

    public packageDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public List<Package> getUnsubscribedPackage(long user_id){
        // No join necessary here

        String sql = "SELECT packages.package_id AS id, package_name, price, start_date, end_date, package_description FROM packages "  +
                "WHERE NOT EXISTS (" +
                "SELECT * FROM package_subscriptions AS subscriptions WHERE subscriptions.package_id=packages.package_id AND subscriptions.customer_id=?" +
                " )";

        List<Map<String,Object>> queried = new ArrayList<>();
        try {
            queried = this.jdbcTemplate.queryForList(sql, new Object[]{user_id});
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return MapPackagesToList(queried);
    }

    @Override
    public List<Package> getSubscribedPackage(long user_id) {
        String sql = "SELECT packages.package_id AS id, package_name, price, start_date, end_date, package_description FROM packages "  +
                "WHERE EXISTS(" +
                "SELECT * FROM package_subscriptions WHERE package_subscriptions.package_id=packages.package_id AND package_subscriptions.customer_id=?" +
                " )";

        List<Map<String,Object>> queried = new ArrayList<>();
        try {
            queried = this.jdbcTemplate.queryForList(sql, new Object[]{user_id});
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return MapPackagesToList(queried);
    }

    @Override
    public void addPackage(long service_id, long user_id) {
        String sql = "INSERT INTO package_subscriptions (package_id, customer_id) VALUES (?,?)";

        this.jdbcTemplate.update(sql, service_id, user_id);
    }

    @Override
    public void unsubscribePackage(long service_id, long user_id) {
        String sql = "DELETE FROM package_subscriptions WHERE package_id=? AND customer_id=?";
        this.jdbcTemplate.update(sql, service_id, user_id);
    }
    /**
     * Extracts the data from a SQL query and encapsulates it within
     * a List of Service Objects.
     * @param tuples
     * @return
     */
    public List<Package> MapPackagesToList(List<Map<String,Object>> tuples){
        List<Package> services = new ArrayList<>();
        for(Map<String,Object> tuple : tuples){
            Package service = new Package();
            service.setPackageID((Long) tuple.get("id"));
            service.setPackageName((String) tuple.get("package_name"));
            service.setPrice((Double)tuple.get("price"));
            service.setDescription((String) tuple.get("package_description"));
            services.add(service);
        }
        // If there's better solution use that statement
        return services;
    }

    public List<Service> MapServicesToList(List<Map<String,Object>> tuples) {
        List<Service> services = new ArrayList<>();
        for (Map<String, Object> tuple : tuples) {
            Service service = new Service();
            service.setServiceID((Long) tuple.get("id"));
            service.setServiceName((String) tuple.get("service_name"));
            service.setDuration((Date) tuple.get("start_date"), (Date) tuple.get("end_date"));
            service.setPrice((Double) tuple.get("price"));
            service.setServiceDescription((String) tuple.get("service_description"));
            services.add(service);
        }
        return services;
    }

    @Override
    public void createPackage(Package pack) {
        // TODO Auto-generated method stub
        String sql2 = "INSERT INTO packages (package_name, package_description, price)" + " VALUES (?,?,?)";
        this.jdbcTemplate.update(sql2, pack.getPackageName(), pack.getDescription(), pack.getPrice());
    }

    @Override
    public List<Service> getSubscribedService(long packageID) {
        String sql = "SELECT services.service_id AS id, service_name, price, start_date, end_date, service_description FROM services "  +
                "WHERE EXISTS(" +
                "SELECT * FROM package_service_relations WHERE package_service_relations.service_id=services.service_id AND package_service_relations.package_id=?" +
                " )";

        List<Map<String,Object>> queried = new ArrayList<>();
        try {
            queried = this.jdbcTemplate.queryForList(sql, new Object[]{packageID});
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return MapServicesToList(queried);
    }

    @Override
    public List<Service> getUnsubscribedService(long packageID) {
        String sql = "SELECT services.service_id AS id, service_name, price, start_date, end_date, service_description FROM services "  +
                "WHERE NOT EXISTS (" +
                "SELECT * FROM package_service_relations WHERE package_service_relations.service_id=services.service_id AND package_service_relations.package_id=?" +
                " )";

        List<Map<String,Object>> queried = new ArrayList<>();
        try {
            queried = this.jdbcTemplate.queryForList(sql, new Object[]{packageID});
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return MapServicesToList(queried);
    }

    @Override
    public void addService(long package_id, long service_id) {
        String sql = "INSERT INTO package_service_relations (package_id, service_id) VALUES (?,?)";

        this.jdbcTemplate.update(sql, package_id, service_id);
    }

    @Override
    public void unsubscribeService(long package_id, long service_id) {
        String sql = "DELETE FROM package_service_relations WHERE package_id=? AND service_id=?";
        this.jdbcTemplate.update(sql, package_id, service_id);
    }


    @Override
    public List<Package> getAllPackage() {
        String sql = "SELECT packages.package_id AS id, package_name, package_description, price FROM packages";

        List<Map<String,Object>> queried = new ArrayList<>();
        try {
            queried = this.jdbcTemplate.queryForList(sql);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return MapPackagesToList(queried);
    }
}
