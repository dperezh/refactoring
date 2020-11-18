package org.exercise.services;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnection {

    Connection getConnection(String user, String password, String url) throws SQLException;
}
