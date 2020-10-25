package controller;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolDatabaseConnectionManager implements DatabaseConnection {
    // TODO: 21/10/20 Añadir url base datos
    private static PoolDatabaseConnectionManager instancia = null;
    private static final String URI_DB = "jdbc/bichico";


    public static PoolDatabaseConnectionManager instancia(){
        if (instancia == null){
            instancia = new PoolDatabaseConnectionManager();
        }
        return instancia;
    }


    public Connection getConnection() throws SQLException{
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
     * @param conn
     * @throws SQLException
     */
    public void releaseConnection(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}