package model.facades;

import controller.ConnectionController;
import model.ComunidadVO;
import model.FactorVO;
import model.RegionVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComunidadFacade {


    private static final String  CONSULTAR_TODAS_COMUNIDADES
            = "SELECT c.nombre, c.lat, c.long, c.id" +
            " FROM bichico.comunidad c ";

    private static final String  CONSULTA_COMUNIDAD
            = "SELECT c.nombre, c.lat, c.long, c.id" +
            " FROM bichico.comunidad c " +
            "WHERE c.nombre=?";

    private static final String  CONSULTA_REGIONES
            = "SELECT r.id, r.nombre, r.lat, r.long " +
            "FROM bichico.region r " +
            "WHERE r.id_com=?";


    private static final String CONSULTA_FACTOR_REGIONES =
            "SELECT r.nombre, r.habitantes, r.lat, r.long, r.id id, f.valor " +
                    "FROM region r, factor f " +
                    "WHERE " +
                    "      r.id = f.id_region AND r.id_com = ? " +
                    "  AND " +
                    "      f.fecha = (" +
                    "            SELECT MAX(fecha) " +
                    "            FROM factor f " +
                    "            WHERE f.id_region = r.id " +
                    "          )";


    /**
     * Devuelve un mapa de las regiones de una comunidad y su factor de riesgo asociado.
     * @param idCom Id de la comunidad a obtener el mapa
     * @return Map<RegionVO, FactorVO> regionesFactorMap
     */
    public static Map<RegionVO, FactorVO> obtenerFactorRegiones(int idCom){
        Map<RegionVO, FactorVO> regionesFactorMap = new HashMap<>();
        Connection connection = null;
        try {
            connection = ConnectionController.getConnection();

            if (connection == null){
                return null;
            }
            PreparedStatement statement = connection.prepareStatement(CONSULTA_FACTOR_REGIONES);
            statement.setInt(1, idCom);
            ResultSet resultSet = statement.executeQuery();
            RegionVO regionVO;
            FactorVO factorVO;
            while (resultSet.next()){
                regionVO = new RegionVO(
                        resultSet.getString("nombre"),
                        resultSet.getFloat("lat"),
                        resultSet.getFloat("long"));
                regionVO.setHabitantes(resultSet.getInt("habitantes"));
                factorVO = new FactorVO(
                        resultSet.getInt("id"),
                        (int)resultSet.getDouble("valor")
                );
                regionesFactorMap.put(regionVO, factorVO);
            }
            statement.close();
            return regionesFactorMap;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            ConnectionController.releaseConnection(connection);
        }
    }

    /**
     * Devuelve una comunidadVO leida de la base de datos
     * @return
     */
    public static ComunidadVO leerComunidad(String comunidad){

        Connection connection = null;
        ComunidadVO comunidadVO = null;

        try {
            connection = ConnectionController.getConnection();
            if (connection == null){
                return null;
            }
            PreparedStatement statement = connection.prepareStatement(CONSULTA_COMUNIDAD);
            statement.setString(1,comunidad);
            ResultSet resultSet = statement.executeQuery();
            int idCom = -1;
            if (resultSet.next()){
                String nombre = resultSet.getString("nombre");
                float latitud = resultSet.getFloat("lat");
                float longitud = resultSet.getFloat("long");
                idCom = resultSet.getInt("id");
                comunidadVO = new ComunidadVO(nombre, latitud, longitud);
            }
            ConnectionController.releaseConnection(connection);
            leerRegiones(comunidadVO, idCom);
            return comunidadVO;
        } catch (Exception e){
        	 e.printStackTrace();
        	 ConnectionController.releaseConnection(connection);
        	 return null;
        }
    }

    /**
     * Lee las regiones de una determinada comunidad de la base de datos
     * Y las asigna a su corresndiente comunidad
     * @param comunidadVO
     * @param idCom
     */
    private static void leerRegiones(ComunidadVO comunidadVO, int idCom){
        Connection connection;
        try {
            connection = ConnectionController.getConnection();
            if (connection != null){
                List<RegionVO> regionVOList = new ArrayList<>();
                int id;
                String nombreRegion;
                float latitud;
                float longitud;
                RegionVO regionVO;
                PreparedStatement statement = connection.prepareStatement(CONSULTA_REGIONES);
                statement.setInt(1,idCom);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    id = resultSet.getInt("id");
                    nombreRegion = resultSet.getString("nombre");
                    latitud = resultSet.getFloat("lat");
                    longitud = resultSet.getFloat("long");
                    regionVO = new RegionVO(nombreRegion, latitud, longitud);
                    regionVO.setId(id);
                    regionVOList.add(regionVO);
                }
                ConnectionController.releaseConnection(connection);	
                comunidadVO.setRegiones(regionVOList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Lista todas las comunidades de la base de datos
     * @return
     */
    public static List<ComunidadVO> listarTodas() {
        Connection connection = null;
        try {
            connection = ConnectionController.getConnection();
            if (connection != null){
                List<ComunidadVO> comunidadVOList = new ArrayList<>();
                int idComunidad;
                String nombreComunidad;
                float latitud;
                float longitud;
                PreparedStatement statement = connection.prepareStatement(CONSULTAR_TODAS_COMUNIDADES);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()){
                    idComunidad = resultSet.getInt("id");
                    nombreComunidad = resultSet.getString("nombre");
                    latitud = resultSet.getFloat("lat");
                    longitud = resultSet.getFloat("long");
                    ComunidadVO comunidadVO = new ComunidadVO(nombreComunidad, latitud, longitud);
                    comunidadVO.setId(idComunidad);
                    comunidadVOList.add(comunidadVO);
                }
                ConnectionController.releaseConnection(connection);
                return comunidadVOList;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            ConnectionController.releaseConnection(connection);
        }
        return null;
    }
}
