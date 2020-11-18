package org.exercise.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2Connection implements DBConnection{

    @Override
    public Connection getConnection(String user, String password, String url) throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", password);

        return DriverManager.getConnection(url, connectionProps);
    }
}
