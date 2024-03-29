package model.facades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.ConnectionController;
import encriptar.Encriptador;
import model.AdminVO;

/**
 * Clase AdminFacade para comprobar los admins de la aplicacion
 * @author sisinf
 */
public class AdminFacade {

	private final static String contarUsuarios = "SELECT count(*) cuenta FROM bichico.admin a WHERE a.nombre = ?";
	private final static String buscamosUsuario = "SELECT a.* FROM bichico.admin a where a.nombre = ?";

	private final static String PASSWORD = "hash_contrasenya";

	/**
	 * Metodo que recibe un adminVO y comprueba si existe en la BBDD 
	 * @param user
	 * @return true | false
	 */
	public static boolean validateAdmin(AdminVO user) {
		boolean result = false;
		Connection conn = null;

		try {
			// Abrimos la conexión e inicializamos los parámetros
			conn = ConnectionController.getConnection();

			PreparedStatement countPs = conn.prepareStatement(contarUsuarios);
			PreparedStatement findPs = conn.prepareStatement(buscamosUsuario);

			countPs.setString(1, user.getNombre());
			findPs.setString(1, user.getNombre());

			// Ejecutamos la consulta
			ResultSet findRs = findPs.executeQuery();
			ResultSet countRs = countPs.executeQuery();

			countRs.next();
			int n = countRs.getInt(1);

			// Leemos resultados
			if (n == 1) {
				// Comparamos contraseñas
				findRs.next();
				String dbpwd = findRs.getString(PASSWORD);
				if (dbpwd.contentEquals(Encriptador.encriptar(user.getHashedPass()))) {
					result = true;
				}
				findRs.next();

			} else {

				result = false;
			}

			// liberamos los recursos utilizados
			findRs.close();
			findPs.close();
			countRs.close();
			countPs.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			ConnectionController.releaseConnection(conn);
		}
		return result;
	}

}
