package com.googleit.telecom.configs;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * TODO :: We should drop all the tables first before creation.
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


            // Create services table
            statement.executeUpdate("DROP TABLE IF EXISTS services;");
            statement.executeUpdate("CREATE TABLE services ("
                    + "service_id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "service_name VARCHAR(40) NOT NULL,"
                    + "price DOUBLE NOT NULL,"
                    + "start_date DATE NOT NULL,"
                    + "end_date DATE NOT NULL,"
                    + "service_description VARCHAR(200) NOT NULL,"
                    + "PRIMARY KEY (service_id)"
                    + ");");

            // TEST service
            statement.executeUpdate("INSERT INTO services(service_name, price, start_date, end_date, service_description)"
                    + "VALUES ('Freenet', '29.00', '2015-01-01', '2015-02-02', 'This is a freebie services! Take advantage.');");

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
            statement.close();
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}