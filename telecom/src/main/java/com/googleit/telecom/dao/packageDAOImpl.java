package com.googleit.telecom.dao;

import com.googleit.telecom.models.items.Package;
import com.googleit.telecom.models.items.Service;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
            Package pkg = new Package();
            pkg.setPackageID((Long) tuple.get("id"));
            pkg.setPackageName((String) tuple.get("package_name"));
            pkg.setPrice((Double) tuple.get("price"));
            pkg.setDescription((String) tuple.get("package_description"));
            pkg.setPackagedServices(getSubscribedService(pkg.getPackageID()));
            services.add(pkg);
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
    public void createPackage(final Package pack) {
        //String sql = "INSERT INTO packages (package_name, package_description, price)" + " VALUES (?,?,?)";
        //this.jdbcTemplate.update(sql, pack.getPackageName(), pack.getDescription(), pack.getPrice());

        final String sql1 = "INSERT INTO packages (package_name, package_description, price) VALUES (?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql1, new String[] { "package_id" });
                ps.setString(1, pack.getPackageName());
                ps.setString(2, pack.getDescription());
                ps.setString(3, String.valueOf(pack.getPrice()));
                return ps;
            }
        }, keyHolder);

        // Set primary key id to user
        pack.setPackageID(keyHolder.getKey().intValue());
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
