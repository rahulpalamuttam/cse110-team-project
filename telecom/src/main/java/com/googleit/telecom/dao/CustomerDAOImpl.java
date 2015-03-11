package com.googleit.telecom.dao;

import com.googleit.telecom.models.items.Package;
import com.googleit.telecom.models.items.Service;
import com.googleit.telecom.models.users.Customer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDAOImpl implements  CustomerDAO {
    private JdbcTemplate jdbcTemplate;

//    @Autowired
//    private UserDAO userDAO;

    public CustomerDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Customer> getCustomers(long customer_rep_id, ServiceDAO serviceDAO, packageDAO PackageDAO) {
        // TODO :: get customers under specific customer rep

        String sql = "select email FROM users WHERE id IN (SELECT customer_id FROM customer_relations WHERE customer_rep_id=?)";
        List<String> newQueried = new ArrayList<>();
        List<Customer> customerList = new ArrayList<>();
        try {
            newQueried = this.jdbcTemplate.queryForList(sql, new Object[]{customer_rep_id}, String.class);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        System.out.println(customer_rep_id);
        for(String email : newQueried){
            System.out.println("Some email " + email);
            customerList.add(getCustomer(email, serviceDAO, PackageDAO));
        }

        System.out.println(customerList);
        return customerList;
    }

    public Customer getCustomer(long customer_id, ServiceDAO serviceDAO, packageDAO PackageDAO){
        String sql = "SELECT id, email, reg_date, threshold, cancellation FROM users WHERE id=?";
        Map queried = new HashMap();
        try {
            queried = this.jdbcTemplate.queryForMap(sql, new Object[]{customer_id});
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        List<Service> subscribedServices = serviceDAO.getSubscribedService(Long.valueOf(customer_id));
        List<Service> unsubscribedServices = serviceDAO.getUnsubscribedService(Long.valueOf(customer_id));
        List<Package> subscribedPackages = PackageDAO.getSubscribedPackage(Long.valueOf(customer_id));
        List<Package> unsubscribedPackages = PackageDAO.getUnsubscribedPackage(Long.valueOf(customer_id));

        Customer cust = new Customer();
        cust.setId(Long.valueOf((Long) queried.get("id")));
        cust.setEmail((String) queried.get("email"));
        cust.setReg_date(queried.get("reg_date").toString());
        cust.getCustomerBill().setThreshold((double) queried.get("threshold"));
        for(Service service : subscribedServices){
            cust.AddService(service);
        }
        for(Package pkg : subscribedPackages){
            cust.AddPackage(pkg);
        }
        cust.getCustomerBill().addCharge((Double) queried.get("cancellation"));
        cust.setUnsubscribedPackages(unsubscribedPackages);
        cust.setUnsubscribedServices(unsubscribedServices);

        return cust;
    }

    public Customer getCustomer(String email, ServiceDAO serviceDAO, packageDAO PackageDAO){
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
        Long customer_id = cust.getId();
        List<Service> subscribedServices = serviceDAO.getSubscribedService(Long.valueOf(customer_id));
        List<Service> unsubscribedServices = serviceDAO.getUnsubscribedService(Long.valueOf(customer_id));
        List<Package> subscribedPackages = PackageDAO.getSubscribedPackage(Long.valueOf(customer_id));
        List<Package> unsubscribedPackages = PackageDAO.getUnsubscribedPackage(Long.valueOf(customer_id));
        for(Service service : subscribedServices){
            cust.AddService(service);
        }
        for(Package pkg : subscribedPackages){
            cust.AddPackage(pkg);
        }

        cust.setUnsubscribedPackages(unsubscribedPackages);
        cust.setUnsubscribedServices(unsubscribedServices);

        return cust;
    }
}
