package com.googleit.telecom.configs;

import com.googleit.telecom.dao.packageDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * TODO :: We should drop all the tables first before creation.
 * hello
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
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            // subscriptions has a foreign key constraint so we need to drop it first

            statement.executeUpdate("DROP TABLE IF EXISTS subscriptions;");
            statement.executeUpdate("DROP TABLE IF EXISTS package_subscriptions;");
            // Create user table
            statement.executeUpdate("SET foreign_key_checks = 0;");
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            statement.executeUpdate("SET foreign_key_checks = 1;");
            statement.executeUpdate("CREATE TABLE users ("
                    + "id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "email VARCHAR(40) NOT NULL,"
                    + "password VARCHAR(60) NOT NULL,"
                    + "reg_date DATE NOT NULL,"
                    + "enabled BOOLEAN NOT NULL,"
                    + "PRIMARY KEY(`id`),"
                    + "UNIQUE KEY `email` (`email`) "
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");

            // Create user attempts table
            statement.executeUpdate("DROP TABLE IF EXISTS user_attempts;");
            statement.executeUpdate("CREATE TABLE user_attempts ("
                    + "id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "email VARCHAR(40) NOT NULL,"
                    + "attempts VARCHAR(45) NOT NULL,"
                    + "PRIMARY KEY (id)"
                    + ");");
            
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
            
            // TEST USER
            // Email : git110@ucsd.edu
            // Password : hello
            statement.executeUpdate("INSERT INTO users(email, password, reg_date, enabled) "
                    + "VALUES ('git110@ucsd.edu', '$2a$10$.aA.L.gBMay4llqXPBbHEue4YIc6Sc80H3NJ0iVBQh0ZKTlRAKM86', '2015-01-01', 1);");
            statement.executeUpdate("INSERT INTO user_roles(email, role) "
                    + "VALUES ('git110@ucsd.edu', 'ROLE_USER');");

            // Email : a
            // Password : a
            statement.executeUpdate("INSERT INTO users(email, password, reg_date, enabled) "
                    + "VALUES ('a', '$2a$10$c/93hxLyT5vZHMUa/zoCaeJsM7VEbPw/17vXbPzzvCptpVfdy/W0a', '2015-02-01', 1);");
            statement.executeUpdate("INSERT INTO user_roles(email, role) "
                    + "VALUES ('a', 'ROLE_USER');");

            // Email : customerRep@gmail.com
            // Password : a
            statement.executeUpdate("INSERT INTO users(email, password, reg_date, enabled) "
                    + "VALUES ('customerRep@gmail.com', '$2a$10$c/93hxLyT5vZHMUa/zoCaeJsM7VEbPw/17vXbPzzvCptpVfdy/W0a', '2015-02-01', 1);");
            statement.executeUpdate("INSERT INTO user_roles(email, role) "
                    + "VALUES ('customerRep@gmail.com', 'ROLE_CUSTOMER_REP');");
            
            // Email : mrep@abc.com
            // Password : a
            statement.executeUpdate("INSERT INTO users(email, password, reg_date, enabled) "
                    + "VALUES ('mrep@abc.com', '$2a$10$c/93hxLyT5vZHMUa/zoCaeJsM7VEbPw/17vXbPzzvCptpVfdy/W0a', '2015-01-01', 1);");
            statement.executeUpdate("INSERT INTO user_roles(email, role) "
                    + "VALUES ('mrep@abc.com', 'ROLE_MARK_REP');");

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
            // Create services table
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

            // Create customer_rep relations table
            statement.executeUpdate("DROP TABLE IF EXISTS customer_relations;");
            statement.executeUpdate("CREATE TABLE customer_relations ("
                    + "relation_id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "customer_id INT UNSIGNED NOT NULL,"
                    + "customer_rep_id INT UNSIGNED NOT NULL,"
                    + "PRIMARY KEY (relation_id)"
                    + ");");

            // TEST customer_rep relations
            statement.executeUpdate("INSERT INTO customer_relations(customer_id,customer_rep_id)" +
                    "VALUES ('1','3');");

            // TEST service
            statement.executeUpdate("INSERT INTO services(service_name, price, start_date, end_date, service_description)"
                    + "VALUES ('Freenet', '00.00', '2015-01-01', '2015-02-02', 'This is a freebie service! Take advantage.');");
            statement.executeUpdate("INSERT INTO services(service_name, price, start_date, end_date, service_description)"
                    + "VALUES ('Freebie', '65.01', '2015-01-01', '2015-02-02', 'This is a newbie service! Take advantage.');");
            statement.executeUpdate("INSERT INTO services(service_name, price, start_date, end_date, service_description)"
                    + "VALUES ('Newbie', '0.01', '2015-01-01', '2015-02-02', 'This is a nono service! Take advantage.');");
            statement.executeUpdate("INSERT INTO services(service_name, price, start_date, end_date, service_description)"
                    + "VALUES ('Newnet', '35.01', '2015-01-01', '2015-02-02', 'This is some service! Take advantage.');");

            // TEST service
            statement.executeUpdate("INSERT INTO packages(package_name, price, start_date, end_date, package_description)"
                    + "VALUES ('Expensive Package', '65.99', '2015-01-01', '2015-02-02', 'This is a freebie service! Take advantage.');");
            statement.executeUpdate("INSERT INTO packages(package_name, price, start_date, end_date, package_description)"
                    + "VALUES ('Freebie Package', '00.00', '2015-01-01', '2015-02-02', 'This is a newbie service! Take advantage.');");


            // Create a subscription table
            // TODO :: Currently only service_id is a foreign key constraint - we need customer to also be one

            statement.executeUpdate("CREATE TABLE subscriptions ("
                    + "subscription_id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "service_id INT UNSIGNED NOT NULL,"
                    + "customer_id INT UNSIGNED NOT NULL,"
                    + "PRIMARY KEY (subscription_id),"
                    + "KEY fk_subscriptions (service_id, customer_id),"
                    + "CONSTRAINT fk_subscriptions FOREIGN KEY (service_id) REFERENCES services(service_id)"
                    + ");");

            // test a subscription table
            statement.executeUpdate("INSERT INTO subscriptions (service_id, customer_id)"
                    + "VALUES ('1', '1')");
            statement.executeUpdate("INSERT INTO subscriptions (service_id, customer_id)"
                    + "VALUES ('2', '1')");

            statement.executeUpdate("CREATE TABLE package_subscriptions ("
                    + "subscription_id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "package_id INT UNSIGNED NOT NULL,"
                    + "customer_id INT UNSIGNED NOT NULL,"
                    + "PRIMARY KEY (subscription_id),"
                    + "KEY fk_package_subscriptions (package_id, customer_id),"
                    + "CONSTRAINT fk_package_subscriptions FOREIGN KEY (package_id) REFERENCES packages(package_id)"
                    + ");");

            statement.close();
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}