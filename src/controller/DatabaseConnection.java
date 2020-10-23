package controller;

import java.sql.Connection;

/**
 *
 */
public interface DatabaseConnection {
    Connection getConnection() throws Exception;

    void releaseConnection(Connection connection) throws Exception;
}
