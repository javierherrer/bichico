package controller;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase que se comunica con el servidor TOMCAT para obtener y liberar conexiones.
 */
public class PoolTomcatConnection implements DatabaseConnection {
    private static PoolTomcatConnection instancia = null;
    private static final String URI_DB = "jdbc/bichicoremoto";


    /**
     * Es singleton
     */
    private PoolTomcatConnection(){

    }

    /**
     * Obtiene la instancia de la conexión
     * @return
     */
    public static PoolTomcatConnection instancia(){
        if (instancia == null){
            instancia = new PoolTomcatConnection();
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