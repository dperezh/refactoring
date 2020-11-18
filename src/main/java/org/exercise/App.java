package org.exercise;

import org.exercise.dao.ILoggerDAO;
import org.exercise.dao.LoggerDAO;
import org.exercise.models.LogType;
import org.exercise.services.DBConnection;
import org.exercise.services.H2Connection;
import org.exercise.services.ILoggerService;
import org.exercise.services.LoggerService;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Map<String, String> dbParams = fillParams();
        DBConnection connection = new H2Connection();
        ILoggerDAO loggerDAO = new LoggerDAO();
        ILoggerService loggerService = new LoggerService(loggerDAO);
        LogManager logManager = new LogManager(loggerService, connection, true, true,
                true, true,true,true, dbParams);
        try {
            logManager.LogMessage("Mensaje de prueba", LogType.WARNING);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static Map<String, String> fillParams() {
        Map<String, String> dbParams = new HashMap<>();
        dbParams.put("userName", "sa");
        dbParams.put("password", "sa");
        dbParams.put("jdbcURL", "jdbc:h2:./src/main/resources/logger");
        dbParams.put("logFileFolder", "./logs");
        return dbParams;
    }
}
