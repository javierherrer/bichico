import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controller.ConnectionManager;
import controller.PoolConnectionManager;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			// Abrimos la conexión e inicializamos los parámetros 
			conn = ConnectionManager.getConnection(); 
			PreparedStatement ps = conn.prepareStatement("SELECT m.* FROM Bichico.mensaje m");
			ResultSet rset = ps.executeQuery();
			while(rset.next()) {
				System.out.println(rset.getString("emisor"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}
	}

}
