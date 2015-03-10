package com.googleit.telecom.configs;

import com.googleit.telecom.dao.packageDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

/**
 * TODO :: We should drop all the tables first before creation.
 * hello bonjour
 */
public class DB {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void init() {
        DataSource dataSource = getDataSource();
        Date curr = new Date();
        DateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sqlFormat.format(curr));
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("SET foreign_key_checks = 0;");

            // Create user table
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            statement.executeUpdate("CREATE TABLE users ("
                    + "id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "email VARCHAR(40) NOT NULL,"
                    + "password VARCHAR(60) NOT NULL,"
                    + "reg_date DATE DEFAULT '" + sqlFormat.format(curr) + "',"
                    + "enabled BOOLEAN NOT NULL,"
                    + "threshold DOUBLE unsigned DEFAULT 0,"
                    + "PRIMARY KEY(`id`),"
                    + "UNIQUE KEY `email` (`email`) "
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");

            // Create user role table
            statement.executeUpdate("DROP TABLE IF EXISTS user_roles;");
            statement.executeUpdate("CREATE TABLE user_roles ("
                    + "role_id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "email VARCHAR(40) NOT NULL,"
                    + "role VARCHAR(50) NOT NULL,"
                    + "PRIMARY KEY (`role_id`),"
                    + "KEY `fk_user_roles` (`email`),"
                    + "CONSTRAINT `fk_user_roles` FOREIGN KEY (`email`) REFERENCES `users` (`email`)"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");

            // Create customer_rep relations table
            statement.executeUpdate("DROP TABLE IF EXISTS customer_relations;");
            statement.executeUpdate("CREATE TABLE customer_relations ("
                    + "relation_id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "customer_id INT UNSIGNED NOT NULL,"
                    + "customer_rep_id INT UNSIGNED NOT NULL,"
                    + "threshold DOUBLE UNSIGNED DEFAULT 0,"
                    + "PRIMARY KEY (relation_id)"
                    + ");");


            // TEST USER
            // Email : git110@ucsd.edu
            // Password : hello
            // Role : Customer
            statement.executeUpdate("INSERT INTO users(email, password, enabled) "
                    + "VALUES ('git110@ucsd.edu', '$2a$10$.aA.L.gBMay4llqXPBbHEue4YIc6Sc80H3NJ0iVBQh0ZKTlRAKM86', 1);");
            statement.executeUpdate("INSERT INTO user_roles(email, role) "
                    + "VALUES ('git110@ucsd.edu', 'ROLE_CUSTOMER');");

            // Email : a
            // Password : a
            // Role : Customer
            statement.executeUpdate("INSERT INTO users(email, password, enabled) "
                    + "VALUES ('a', '$2a$10$c/93hxLyT5vZHMUa/zoCaeJsM7VEbPw/17vXbPzzvCptpVfdy/W0a', 1);");
            statement.executeUpdate("INSERT INTO user_roles(email, role) "
                    + "VALUES ('a', 'ROLE_CUSTOMER');");

            // Email : customerRep@gmail.com
            // Password : a
            // Role : Customer Rep
            statement.executeUpdate("INSERT INTO users(email, password, enabled) "
                    + "VALUES ('crep', '$2a$10$c/93hxLyT5vZHMUa/zoCaeJsM7VEbPw/17vXbPzzvCptpVfdy/W0a', 1);");
            statement.executeUpdate("INSERT INTO user_roles(email, role) "
                    + "VALUES ('crep', 'ROLE_CUSTOMER_REP');");
            
            // Email : mrep@abc.com
            // Password : a
            // Role : Marketing Rep
            statement.executeUpdate("INSERT INTO users(email, password, enabled) "
                    + "VALUES ('mrep', '$2a$10$c/93hxLyT5vZHMUa/zoCaeJsM7VEbPw/17vXbPzzvCptpVfdy/W0a', 1);");
            statement.executeUpdate("INSERT INTO user_roles(email, role) "
                    + "VALUES ('mrep', 'ROLE_MARK_REP');");

            // TEST customer_rep relations
            statement.executeUpdate("INSERT INTO customer_relations(customer_id,customer_rep_id)"
                    + "VALUES ('1','3');");

            // Create services table
            statement.executeUpdate("DROP TABLE IF EXISTS services;");
            statement.executeUpdate("CREATE TABLE services ("
                    + "service_id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "service_name VARCHAR(40) NOT NULL,"
                    + "price DOUBLE NOT NULL,"
                    + "start_date DATE,"
                    + "end_date DATE,"
                    + "service_description VARCHAR(200) NOT NULL,"
                    + "PRIMARY KEY (service_id)"
                    + ");");

            // Create packages table
            statement.executeUpdate("DROP TABLE IF EXISTS packages;");
            statement.executeUpdate("CREATE TABLE packages ("
                    + "package_id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "package_name VARCHAR(40) NOT NULL,"
                    + "price DOUBLE NOT NULL,"
                    + "start_date DATE,"
                    + "end_date DATE,"
                    + "package_description VARCHAR(200) NOT NULL,"
                    + "PRIMARY KEY (package_id)"
                    + ");");

            // Create a package and service relations table
            statement.executeUpdate("DROP TABLE IF EXISTS package_service_relations;");
            statement.executeUpdate("CREATE TABLE package_service_relations ("
                    + "relation_id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "package_id INT UNSIGNED NOT NULL,"
                    + "service_id INT UNSIGNED NOT NULL,"
                    + "PRIMARY KEY (relation_id)"
                    + ");");

            // Create a service subscription table
            // TODO :: Currently only service_id is a foreign key constraint - we need customer to also be one
            statement.executeUpdate("DROP TABLE IF EXISTS service_subscriptions;");
            statement.executeUpdate("CREATE TABLE service_subscriptions ("
                    + "subscription_id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "service_id INT UNSIGNED NOT NULL,"
                    + "customer_id INT UNSIGNED NOT NULL,"
                    + "PRIMARY KEY (subscription_id),"
                    + "KEY fk_subscriptions (service_id, customer_id),"
                    + "CONSTRAINT fk_subscriptions FOREIGN KEY (service_id) REFERENCES services(service_id)"
                    + ");");

            // Create a package subscription table
            statement.executeUpdate("DROP TABLE IF EXISTS package_subscriptions;");
            statement.executeUpdate("CREATE TABLE package_subscriptions ("
                    + "subscription_id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "package_id INT UNSIGNED NOT NULL,"
                    + "customer_id INT UNSIGNED NOT NULL,"
                    + "PRIMARY KEY (subscription_id),"
                    + "KEY fk_package_subscriptions (package_id, customer_id),"
                    + "CONSTRAINT fk_package_subscriptions FOREIGN KEY (package_id) REFERENCES packages(package_id)"
                    + ");");

            // TEST service
            statement.executeUpdate("INSERT INTO services(service_name, price, start_date, end_date, service_description)"
                    + "VALUES ('Freenet', '00.00', '2015-01-01', '2015-02-02', 'This is a freebie service! Take advantage.'),"
                    + "('Freebie', '65.01', '2015-01-01', '2015-02-02', 'This is a newbie service! Take advantage.'),"
                    + "('Newbie',  '0.01',  '2015-01-01', '2015-02-02', 'This is a nono service! Take advantage.'),"
                    + "('Newnet',  '35.01', '2015-01-01', '2015-02-02', 'This is some service! Take advantage.');");

            // TEST package
            statement.executeUpdate("INSERT INTO packages(package_name, price, start_date, end_date, package_description)"
                    + "VALUES ('Expensive Package', '65.99', '2015-01-01', '2015-02-02', 'This is a freebie service! Take advantage.'),"
                    + "('Freebie Package', '00.00', '2015-01-01', '2015-02-02', 'This is a newbie service! Take advantage.');");

            // TEST a subscription table
            statement.executeUpdate("INSERT INTO service_subscriptions (service_id, customer_id)"
                    + "VALUES ('1', '1'), ('2','1')");

            // TEST package and service relation
            statement.executeUpdate("INSERT INTO package_service_relations(package_id, service_id)" +
                    "VALUES ('1','1'), ('1','2');");

            statement.executeUpdate("SET foreign_key_checks = 1;");

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}