package model.facades;

import controller.ConnectionController;
import model.ComunidadVO;
import model.RegionVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase RegionFacade para trabajar con region
 * 
 * @author sisinf
 */
public class RegionFacade {
	private static final String ID = "id";
	private static final String HABITANTES = "habitantes";
	private static final String NOMBRE = "nombre";
	private static final String LATITUD = "lat";
	private static final String LONGITUD = "long";
	private static final String COMUNIDAD = "id_com";

	private final static String CONSULTA_REGIONES = "SELECT * FROM Bichico.region r " +
			"where r.id_com = (select c.id from Bichico.comunidad c where c.nombre = ?)";

	private final static String CONSULTA_LATITUD = "SELECT r.lat " + "FROM Bichico.region r " + "WHERE r.id = ?";

	private final static String CONSULTA_LONGITUD = "SELECT r.long " + "FROM Bichico.region r " + "WHERE r.id = ?";

	/**
	 * Obtiene una lista de regiones en la base de datos
	 *
	 * @return List<RegionVO>
	 */
	public List<RegionVO> obtenerRegiones(ComunidadVO comunidadVO) {
		List<RegionVO> listaRegiones = new ArrayList<>();
		Connection connection;
		try {
			connection = ConnectionController.getConnection();
			if (connection == null) {
				return null;
			}
			PreparedStatement statement = connection.prepareStatement(CONSULTA_REGIONES);
			statement.setString(1, comunidadVO.getNombre());
			System.out.println(statement);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				listaRegiones.add(new RegionVO(resultSet.getInt(ID), resultSet.getInt(HABITANTES),
						resultSet.getString(NOMBRE), resultSet.getString(COMUNIDAD), resultSet.getFloat(LATITUD),
						resultSet.getFloat(LONGITUD)));
			}
			statement.close();
			resultSet.close();
			ConnectionController.releaseConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaRegiones;
	}

	/**
	 * Obtiene la longitud de una determinada region
	 *
	 * @param regionVO
	 * @return float longitud de una region
	 */
	public float obtenerLongitud(RegionVO regionVO) {
		Connection connection = null;
		float longitud = 0;
		try {
			connection = ConnectionController.getConnection();
			if (connection == null) {
				return longitud;
			}
			PreparedStatement statement = connection.prepareStatement(CONSULTA_LONGITUD);
			statement.setInt(1, regionVO.getId());
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				longitud = resultSet.getFloat(LONGITUD);
			}
			statement.close();
			resultSet.close();
			ConnectionController.releaseConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionController.releaseConnection(connection);
		}
		return longitud;
	}

	/**
	 * Obtiene la latitud de una región
	 *
	 * @param regionVO
	 * @return float devuelve latitud de una region
	 */
	public static float obtenerLatitud(RegionVO regionVO) {
		Connection connection = null;
		float latitud = 0;
		try {
			connection = ConnectionController.getConnection();
			if (connection == null) {
				return latitud;
			}
			PreparedStatement statement = connection.prepareStatement(CONSULTA_LATITUD);
			statement.setInt(1, regionVO.getId());
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				latitud = resultSet.getFloat(LATITUD);
			}
			statement.close();
			resultSet.close();
			ConnectionController.releaseConnection(connection);

		} catch (SQLException e) {
			ConnectionController.releaseConnection(connection);
			e.printStackTrace();
		}
		return latitud;
	}
}
