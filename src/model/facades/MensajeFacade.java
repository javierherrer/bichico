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

/**
 * Clase MensajeFacade para trabajar con mensajes
 * 
 * @author sisinf
 */
public class MensajeFacade {

	private final static String MOSTRAR_MENSAJES = "SELECT m.* FROM Bichico.mensaje m";

	private final static String INSERTAR_MENSAJE = "INSERT INTO bichico.mensaje (emisor,email,contenido) VALUES (? , ? , ? )";

	private final static String ELIMINAR_MENSAJE = "DELETE FROM bichico.mensaje WHERE id = ?";

	/**
	 * metodo que devuelve una lista de mensajes
	 * @return List<MensajeVO>
	 */
	public static List<MensajeVO> consultarMensajes() {
		List<MensajeVO> listaMensajes = new ArrayList<>();

		Connection conn = null;

		try {
			// Abrimos la conexión e inicializamos los parámetros
			conn = ConnectionController.getConnection();
			PreparedStatement ps = conn.prepareStatement(MOSTRAR_MENSAJES);
			ResultSet rset = ps.executeQuery();

			while (rset.next()) {
				int id = rset.getInt("id");
				String emisor = rset.getString("emisor");
				String email = rset.getString("email");
				String contenido = rset.getString("contenido");

				listaMensajes.add(new MensajeVO(id, emisor, contenido, email));
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
	/**
	 * metodo que comprueba que un mensaje es correcto.
	 * @param mensaje
	 * @return
	 */
	boolean comprobarMensage(MensajeVO mensaje) {

		if (mensaje.getContenido() != null && mensaje.getEmail() != null && mensaje.getEmisor() != null) {
			return true;
		}
		return false;
	}
	/**
	 * metodo que inserta un mensaje en la base de datos
	 * @param mensaje
	 * @return true | false si los datos son correctos
	 */
	public static boolean enviarMensaje(MensajeVO mensaje) {
		Connection conn = null;

		// Abrimos la conexión e inicializamos los parámetros
		try {
			conn = ConnectionController.getConnection();

			PreparedStatement ps = conn.prepareStatement(INSERTAR_MENSAJE);
			ps.setString(1, mensaje.getEmisor());
			ps.setString(2, mensaje.getEmail());
			ps.setString(3, mensaje.getContenido());
			int tablasAfectadas = ps.executeUpdate();

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

	/**
	 * metodo que elimina un mensaje por su id
	 * @param id
	 * @return devuelve las celdas afectas
	 */
	public static int eliminarMensaje(int id) {
		int affectedrows = 0;
		Connection conn = null;
		try {
			conn = ConnectionController.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(ELIMINAR_MENSAJE);

			pstmt.setInt(1, id);

			affectedrows = pstmt.executeUpdate();

			pstmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			ConnectionController.releaseConnection(conn);
		} finally {
			ConnectionController.releaseConnection(conn);
		}
		return affectedrows;
	}
}
