package model.facades;

import controller.PoolConnectionManager;
import model.ComunidadVO;
import model.RegionVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una Region
 */
public class RegionFacade {
    private static final String ID = "id";
    private static final String HABITANTES = "habitantes";
    private static final String NOMBRE = "nombre";
    private static final String LATITUD = "latitud";
    private static final String LONGITUD = "longitud";
    private static final String COMUNIDAD = "comunidad";

    private final static String CONSULTA_REGIONES =
            "SELECT * FROM Bichico.region r WHERE r.comunidad=?";

    private final static String CONSULTA_LATITUD =
            "SELECT r.latitud " +
            "FROM Bichico.region r " +
            "WHERE r.id=?";

    private final static String CONSULTA_LONGITUD =
            "SELECT r.longitud " +
            "FROM Bichico.region r " +
            "WHERE r.id=?";

    /**
     * Obtiene una lista de regiones en la base de datos
     * @return List<RegionVO>
     */
    public static List<RegionVO> obtenerRegiones(ComunidadVO comunidadVO){
        List<RegionVO> listaRegiones = new ArrayList<>();
        Connection connection;
        try {
            connection = PoolConnectionManager.getConnection();
            if (connection == null) {
                return null;
            }
            PreparedStatement statement = connection.prepareStatement(CONSULTA_REGIONES);
            statement.setString(1, comunidadVO.getNombre());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                listaRegiones.add(
                        new RegionVO(
                                resultSet.getInt(ID),
                                resultSet.getInt(HABITANTES),
                                resultSet.getString(NOMBRE),
                                resultSet.getString(COMUNIDAD),
                                resultSet.getFloat(LATITUD),
                                resultSet.getFloat(LONGITUD)
                                ));
            }
            statement.close();
            resultSet.close();
            PoolConnectionManager.releaseConnection(connection);
            return listaRegiones;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtiene la longitud de una determinada region
     * @param regionVO
     * @return
     */
    public static float obtenerLongitud(RegionVO regionVO){
        Connection connection;
        float longitud = 0;
        try {
            connection = PoolConnectionManager.getConnection();
            if (connection == null) {
                return longitud;
            }
            PreparedStatement statement = connection.prepareStatement(CONSULTA_LONGITUD);
            statement.setInt(1,regionVO.getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                longitud = resultSet.getFloat(LONGITUD);
            }
            statement.close();
            resultSet.close();
            PoolConnectionManager.releaseConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return longitud;
    }

    /**
     * Obtiene la latitud de una región
     * @param regionVO
     * @return
     */
    public static float obtenerLatitud(RegionVO regionVO){
        Connection connection;
        float latitud = 0;
        try {
            connection = PoolConnectionManager.getConnection();
            if (connection == null) {
                return latitud;
            }
            PreparedStatement statement = connection.prepareStatement(CONSULTA_LATITUD);
            statement.setInt(1,regionVO.getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                latitud = resultSet.getFloat(LATITUD);
            }
            statement.close();
            resultSet.close();
            PoolConnectionManager.releaseConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return latitud;
    }
}
