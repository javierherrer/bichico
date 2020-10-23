package model.facades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.PoolConnectionManager;
import model.MensajeVO;

public class MensajeFacade {

	private static String mostrarMensajes = "SELECT m.* FROM Bichico.mensaje m";
	private static String mostrarMensajeUsuario = "Select * from mensaje where emisor= ?";
	
	public void mostrarMensajes() {
		Connection conn = null;
		try {
			// Abrimos la conexión e inicializamos los parámetros 
			conn = PoolConnectionManager.getConnection(); 
			PreparedStatement ps = conn.prepareStatement(mostrarMensajes);
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
	
	public MensajeVO getUser(String emisor) {
		Connection conn = null;
		MensajeVO user = null;

		try {
			// Abrimos la conexión e inicializamos los parámetros 
			conn = PoolConnectionManager.getConnection(); 
			PreparedStatement ps = conn.prepareStatement(mostrarMensajeUsuario);
			ps.setString(1, emisor);
			ResultSet rset = ps.executeQuery();
			rset.next();
			user = new MensajeVO(Integer.parseInt(rset.getString("id")), 
								rset.getString("emisor"), 
								rset.getString("contenido"),
								rset.getString("email"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}
		return user;
	}
}
