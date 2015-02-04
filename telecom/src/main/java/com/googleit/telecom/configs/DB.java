package com.googleit.telecom.configs;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

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
            statement.executeUpdate("DROP TABLE IF EXISTS user_roles");
            statement.executeUpdate("CREATE TABLE user_roles ("
                    + "role_id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "email VARCHAR(40) NOT NULL,"
                    + "role VARCHAR(50) NOT NULL,"
                    + "PRIMARY KEY (`role_id`),"
                    + "KEY `fk_user_roles` (`email`),"
                    + "CONSTRAINT `fk_user_roles` FOREIGN KEY (`email`) REFERENCES `users` (`email`)"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
            
            // TEST USER
            statement.executeUpdate("INSERT INTO users(email, password, reg_date, enabled) "
                    + "VALUES ('git110@ucsd.edu', '$2a$10$.aA.L.gBMay4llqXPBbHEue4YIc6Sc80H3NJ0iVBQh0ZKTlRAKM86', '2015-01-01', 1);");
            statement.executeUpdate("INSERT INTO user_roles(email, role) "
                    + "VALUES ('git110@ucsd.edu', 'ROLE_USER');");
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}