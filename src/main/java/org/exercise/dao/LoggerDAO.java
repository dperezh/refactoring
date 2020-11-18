package org.exercise.dao;

import java.sql.SQLException;
import java.sql.Statement;

public class LoggerDAO implements ILoggerDAO{

    @Override
    public void createTableIfNotExists(Statement statement) throws SQLException {
        statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS `Log_Values`(`message` varchar (255), `type` number )"
        );
    }

    @Override
    public void insertLogValues(Statement statement, String messageText, int type) throws SQLException {
        statement.executeUpdate(
                "insert into `Log_Values` values('" + messageText + "', " + type + ")"
        );
    }
}
