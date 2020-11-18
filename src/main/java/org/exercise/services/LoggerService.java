package org.exercise.services;

import org.exercise.dao.ILoggerDAO;
import org.exercise.models.LogType;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;

public class LoggerService implements ILoggerService{

    private final ILoggerDAO loggerDAO;

    public LoggerService(ILoggerDAO loggerDAO) {
        this.loggerDAO = loggerDAO;
    }

    @Override
    public void createTableIfNotExists(Statement statement) throws SQLException {
        loggerDAO.createTableIfNotExists(statement);
    }

    @Override
    public void insertLogValues(Statement statement, String message, int type) throws SQLException {
        loggerDAO.insertLogValues(statement, message, type);
    }

    public String getResultantMessage(LogType logType, String messageText) {
        return logType + " " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " " + messageText;
    }
}
