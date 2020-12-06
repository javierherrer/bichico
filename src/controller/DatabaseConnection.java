package controller;

import java.sql.Connection;

/**
 * Clase antigua de control de conexion.
 * @author sisinf
 * @deprecated
 */
public interface DatabaseConnection {
    Connection getConnection() throws Exception;

    void releaseConnection(Connection connection) throws Exception;
}
