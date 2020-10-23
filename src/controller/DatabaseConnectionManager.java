package controller;
import java.sql.*;

/**  * Clase que abstrae la conexion con la base de datos.  */

public class DatabaseConnectionManager implements DatabaseConnection {
	// JDBC nombred el driver y URL de BD 
	private static final String JDBC_DRIVER = "org.postgresql.Driver";  
	private static final String HOST = "139.59.205.11";
	private static final String PORT = "9999";
	private static final String DB_URL =
			"jdbc:postgresql://" + HOST +":" + PORT + "/postgres?currentSchema=bichito";

	// Credenciales de la Base de Datos
	private static final String USER = "admin";
	private static final String PASS = "123456";

	@Override
	public Connection getConnection() {
		Connection conn = null;
		try{
			//STEP 1: Register JDBC driver
			Class.forName(JDBC_DRIVER);
			//STEP 2: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void releaseConnection(Connection connection) throws Exception {
		connection.close();
	}
}
