import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controller.ConnectionController;
import controller.DatabaseConnectionManager;

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			// Abrimos la conexión e inicializamos los parámetros 
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

}
