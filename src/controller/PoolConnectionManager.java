package controller;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnectionManager {
    // TODO: 21/10/20 Añadir url base datos 
    private static final String URI_DB = "";
    
    public static Connection getConnection() throws SQLException{
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");

            DataSource dataSource = (DataSource) envCtx.lookup(URI_DB);

            return dataSource.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Releases the connection to the pool
     * @param connection
     * @throws SQLException
     */
    public static void releaseConnection(Connection connection) throws SQLException{
        connection.close();
    }


}