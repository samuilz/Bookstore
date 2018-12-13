package com.zahariev.bookstore.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@PropertySource("classpath:application.properties")
public class ConnectionHelper {
    private String dbUrl;
    private String username;
    private String password;

    @Autowired
    public ConnectionHelper(Environment environment) {
        this.dbUrl = environment.getProperty("database.url");
        this.username = environment.getProperty("database.username");
        this.password = environment.getProperty("database.password");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, username, password);
    }
}
