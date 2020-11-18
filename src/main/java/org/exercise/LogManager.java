package org.exercise;

import org.exercise.models.LogType;
import org.exercise.services.DBConnection;
import org.exercise.services.ILoggerService;
import org.exercise.utils.FileUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogManager {

    private static ILoggerService loggerService;
    private static DBConnection connection;
    private static boolean logToFile;
    private static boolean logToConsole;
    private static boolean logMessage;
    private static boolean logWarning;
    private static boolean logError;
    private static boolean logToDatabase;
    private static Map<String, String> params;
    private static Logger logger;

    public LogManager(ILoggerService loggerServiceParam, DBConnection connectionParam,
                      boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam,
                      boolean logMessageParam, boolean logWarningParam, boolean logErrorParam,
                      Map<String, String> paramsMap) {
        loggerService = loggerServiceParam;
        connection = connectionParam;
        logger = Logger.getLogger("MyLog");
        logError = logErrorParam;
        logMessage = logMessageParam;
        logWarning = logWarningParam;
        logToDatabase = logToDatabaseParam;
        logToFile = logToFileParam;
        logToConsole = logToConsoleParam;
        params = paramsMap;
    }

    public void LogMessage(String messageText, LogType logType) throws Exception {

        if (messageText == null || messageText.length() == 0) {
            return;
        }
        if (!logToConsole && !logToFile && !logToDatabase) {
            throw new Exception("Invalid configuration");
        }
        if ((!logError && !logMessage && !logWarning)) {
            throw new Exception("Error or Warning or Message must be specified");
        }

        Level logLevel = null;
        String resultantMessage = null;

        if (logType.equals(LogType.MESSAGE) && logMessage) {
            logLevel = Level.INFO;
            resultantMessage = loggerService.getResultantMessage(logType, messageText);
        } else if (logType.equals(LogType.ERROR) && logError) {
            logLevel = Level.SEVERE;
            resultantMessage = loggerService.getResultantMessage(logType, messageText);
        } else if (logType.equals(LogType.WARNING) && logWarning) {
            logLevel = Level.WARNING;
            resultantMessage = loggerService.getResultantMessage(logType, messageText);
        }

        printLogs(logLevel, resultantMessage);
    }

    public static void printLogs(Level logLevel, String resultantMessage) throws SQLException, IOException {
        try (Statement statement = connection.getConnection(
                params.get("userName"),
                params.get("password"),
                params.get("jdbcURL")).createStatement()) {

            if (logToFile) {
                FileHandler fileHandler = FileUtil.createFileIfNotExists(params.get("logFileFolder"), "logs");
                logger.addHandler(fileHandler);
                logger.setUseParentHandlers(false);
                logger.log(logLevel, resultantMessage);
                logger.removeHandler(fileHandler);
            }

            if (logToConsole) {
                ConsoleHandler consoleHandler = new ConsoleHandler();
                logger.addHandler(consoleHandler);
                logger.log(logLevel, resultantMessage);
            }

            if (logToDatabase) {
                loggerService.createTableIfNotExists(statement);
                loggerService.insertLogValues(statement, resultantMessage, logLevel != null ? logLevel.intValue() : 0);
            }
        }
    }
}
