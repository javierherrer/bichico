import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.ConnectionController;

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//testTomcat();
		testRemoto();
	}

	private static void testTomcat() {
		Connection conn = null;
		try {
			// Abrimos la conexión e inicializamos los parámetros
			ConnectionController.changeConnection(ConnectionController.TOMCAT_LOCAL);
			conn = ConnectionController.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT m.* FROM Bichico.mensaje m");
			ResultSet rset = ps.executeQuery();
			while(rset.next()) {
				System.out.println(rset.getString("emisor"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionController.releaseConnection(conn);
		}
	}

	private static void testRemoto(){
		Connection connection =null;
		try {
			ConnectionController.changeConnection(ConnectionController.REMOTA);
			connection = ConnectionController.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT t.*" +
					"                 FROM bichico.admin t" +
					"                 LIMIT 501");
			ResultSet rset = ps.executeQuery();
			while(rset.next()) {
				System.out.println(rset.getString("nombre"));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
