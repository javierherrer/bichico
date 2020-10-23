package controller;

import java.sql.Connection;

public interface ConnectionFacade {
    Connection getConnection() throws Exception;

    void releaseConnection(Connection connection) throws Exception;
}
