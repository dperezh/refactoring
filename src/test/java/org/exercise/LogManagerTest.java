package org.exercise;

import org.exercise.dao.ILoggerDAO;
import org.exercise.dao.LoggerDAO;
import org.exercise.models.LogType;
import org.exercise.services.DBConnection;
import org.exercise.services.H2Connection;
import org.exercise.services.ILoggerService;
import org.exercise.services.LoggerService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class LogManagerTest {

    private LogManager logManager;

    @Test
    public void whenNoPassLogLevelThenThrowException() {
        String exceptionMessage = "";
        Map<String, String> dbParams = fillParams();
        DBConnection connection = new H2Connection();
        ILoggerDAO loggerDAO = new LoggerDAO();
        ILoggerService loggerService = new LoggerService(loggerDAO);
        logManager = new LogManager(loggerService, connection, true, true,
                true, false,false,false, dbParams);
        try {
            logManager.LogMessage("This is a test", LogType.WARNING);
        } catch (Exception exception) {
            exceptionMessage = exception.getMessage();
            exception.printStackTrace();
        }
        assertEquals("Error or Warning or Message must be specified", exceptionMessage);
    }

    @Test
    public void whenNoPassLogToThenThrowException() {
        String exceptionMessage = "";
        Map<String, String> dbParams = fillParams();
        DBConnection connection = new H2Connection();
        ILoggerDAO loggerDAO = new LoggerDAO();
        ILoggerService loggerService = new LoggerService(loggerDAO);
        logManager = new LogManager(loggerService, connection, false, false,
                false, true,true,true, dbParams);
        try {
            logManager.LogMessage("This is a test", LogType.WARNING);
        } catch (Exception exception) {
            exceptionMessage = exception.getMessage();
            exception.printStackTrace();
        }
        assertEquals("Invalid configuration", exceptionMessage);
    }

    @Test
    public void whenPassAllOkData() {
        Map<String, String> dbParams = fillParams();
        DBConnection connection = new H2Connection();
        ILoggerDAO loggerDAO = new LoggerDAO();
        ILoggerService loggerService = new LoggerService(loggerDAO);
        logManager = new LogManager(loggerService, connection, true, true,
                true, true,true,true, dbParams);
        try {
            logManager.LogMessage("This is a test", LogType.WARNING);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Ignore
    public static Map<String, String> fillParams() {
        Map<String, String> dbParams = new HashMap<>();
        dbParams.put("userName", "sa");
        dbParams.put("password", "sa");
        dbParams.put("jdbcURL", "jdbc:h2:./src/main/resources/logger");
        dbParams.put("logFileFolder", "./logs");
        return dbParams;
    }
}