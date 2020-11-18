package model.facades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ConnectionController;
import model.MensajeVO;
import model.PalabraVO;

public class MensajeFacade {


	private final static String MOSTRAR_MENSAJES =
			"SELECT m.* FROM Bichico.mensaje m";

	private final static String INSERTAR_MENSAJE =
			"INSERT INTO bichico.mensaje (emisor,email,contenido) VALUES (? , ? , ? )";

	//private static String mostrarMensajeUsuario = "Select * from mensaje where emisor= ?";

	
	public static List<MensajeVO> consultarMensajes() {
		List<MensajeVO> listaMensajes = new ArrayList<>();

		Connection conn = null;

		try {
			// Abrimos la conexión e inicializamos los parámetros 
			conn = ConnectionController.getConnection();
			PreparedStatement ps = conn.prepareStatement(MOSTRAR_MENSAJES);
			ResultSet rset = ps.executeQuery();

			while(rset.next()) {
				String emisor = rset.getString("emisor");
				String email = rset.getString("email");
				String contenido = rset.getString("contenido");

				listaMensajes.add(new MensajeVO(emisor, contenido, email));
			}
			rset.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionController.releaseConnection(conn);
		}

		return listaMensajes;
	}
	
	boolean comprobarMensage(MensajeVO mensaje) {

		if(mensaje.getContenido() != null &&
				mensaje.getEmail() != null &&
				mensaje.getEmisor() != null) {
					return true;
		}
		return false;
	}


	public static boolean enviarMensaje(MensajeVO mensaje) {
			Connection conn = null;

			// Abrimos la conexión e inicializamos los parámetros 
			try {
				conn = ConnectionController.getConnection();
//	private static String insertarMensaje = "INSERT INTO bichico.mensaje (emisor,email,contenido) VALUES (? , ? , ? )";

				PreparedStatement ps = conn.prepareStatement(INSERTAR_MENSAJE);
				ps.setString(1, mensaje.getEmisor());
				ps.setString(2, mensaje.getEmail());
				ps.setString(3, mensaje.getContenido());
				System.out.println(ps);
				int tablasAfectadas = ps.executeUpdate();
				System.out.println(tablasAfectadas);

				ps.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();

				return false;
			} finally {
				ConnectionController.releaseConnection(conn);
			}
			return true;

	}
}
