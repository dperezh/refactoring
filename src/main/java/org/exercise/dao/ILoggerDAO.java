package org.exercise.dao;

import java.sql.SQLException;
import java.sql.Statement;

public interface ILoggerDAO {

    void createTableIfNotExists(Statement statement) throws SQLException;
    void insertLogValues(Statement statement, String message, int type) throws SQLException;
}
