package model.facades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.PoolConnectionManager;
import model.MensajeVO;

public class MensajeFacade {

	private static String mostrarMensajes = "SELECT m.* FROM Bichico.mensaje m";
	private static String insertarMensaje = "INSERT INTO bichico.mensaje (emisor,email,contenido) VALUES (? , ? , ? )";
	
	public void mostrarMensajes() {
		Connection conn = null;
		try {
			// Abrimos la conexión e inicializamos los parámetros 
			conn = PoolConnectionManager.getConnection(); 
			PreparedStatement ps = conn.prepareStatement(mostrarMensajes);
			ResultSet rset = ps.executeQuery();
			while(rset.next()) {
				System.out.println(rset.getString("emisor"));
				System.out.println(rset.getString("email"));
				System.out.println(rset.getString("contenido"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			PoolConnectionManager.releaseConnection(conn);
		}
	}
	
	boolean comprobarMensage(MensajeVO mensaje) {
		
		if(mensaje.getContenido() != null && 
				mensaje.getEmail() != null &&
				mensaje.getEmisor() != null) {
					return true;
		}
		return false;
	}
	
	public boolean enviarMensaje(MensajeVO mensaje) {
			Connection conn = null;
	
			// Abrimos la conexión e inicializamos los parámetros 
			try {
				conn = PoolConnectionManager.getConnection();
//	private static String insertarMensaje = "INSERT INTO bichico.mensaje (emisor,email,contenido) VALUES (? , ? , ? )";
			
			PreparedStatement ps = conn.prepareStatement(insertarMensaje);
			ps.setString(1, mensaje.getEmisor());
			ps.setString(2, mensaje.getEmail());
			ps.setString(3, mensaje.getContenido());
			System.out.println(ps);
			int tablasAfectadas = ps.executeUpdate();
			System.out.println(tablasAfectadas);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return true;
	}
}
