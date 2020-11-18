package org.exercise.services;

import org.exercise.models.LogType;

import java.sql.SQLException;
import java.sql.Statement;

public interface ILoggerService {

    void createTableIfNotExists(Statement statement) throws SQLException;
    void insertLogValues(Statement statement, String message, int type) throws SQLException;
    String getResultantMessage(LogType logType, String messageText);
}
