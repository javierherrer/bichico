package model.facades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.PoolConnectionManager;
import model.MensajeVO;

public class MensajeFacade {

	private static String MOSTRAR_MENSAJE = "SELECT m.* FROM Bichico.mensaje m";

	private static String INSERTAR_MENSAJE = "INSERT INTO bichico.mensaje (emisor,email,contenido) VALUES (? , ? , ? )";

	
	public void mostrarMensajes() {			//se puede hacer un obetner
		Connection conn = null;
		try {
			// Abrimos la conexión e inicializamos los parámetros 
			conn = PoolConnectionManager.getConnection(); 
			PreparedStatement ps = conn.prepareStatement(MOSTRAR_MENSAJE);
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
			Connection connection = null;
			boolean resultado = true;
			// Abrimos la conexión e inicializamos los parámetros 
			try {
				connection = PoolConnectionManager.getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERTAR_MENSAJE);
				ps.setString(1, mensaje.getEmisor());
				ps.setString(2, mensaje.getEmail());
				ps.setString(3, mensaje.getContenido());
			
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				resultado = false;
			}finally {
				PoolConnectionManager.releaseConnection(connection); 
			} 
			return resultado;
	}
}
