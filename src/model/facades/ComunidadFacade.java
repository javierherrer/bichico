package model.facades;

import controller.ConnectionController;
import model.ComunidadVO;
import model.RegionVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComunidadFacade {


    private static final String  CONSULTAR_TODAS_COMUNIDADES
            = "SELECT c.nombre, c.lat, c.long, c.id" +
            " FROM bichico.comunidad c ";

    private static final String  CONSULTA_COMUNIDAD
            = "SELECT c.nombre, c.lat, c.long, c.id" +
            " FROM bichico.comunidad c " +
            "WHERE c.nombre=?";

    private static final String  CONSULTA_REGIONES
            = "SELECT r.nombre, r.lat, r.long " +
            "FROM bichico.region r " +
            "WHERE r.id_com=?";


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
            leerRegiones(comunidadVO, idCom);
            return comunidadVO;
        } catch (Exception e){
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
                String nombreRegion;
                float latitud;
                float longitud;
                PreparedStatement statement = connection.prepareStatement(CONSULTA_REGIONES);
                statement.setInt(1,idCom);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    nombreRegion = resultSet.getString("nombre");
                    latitud = resultSet.getFloat("lat");
                    longitud = resultSet.getFloat("long");
                    regionVOList.add(new RegionVO(nombreRegion, latitud, longitud));
                }

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
        Connection connection;
        try {
            connection = ConnectionController.getConnection();
            if (connection != null){
                List<ComunidadVO> comunidadVOList = new ArrayList<>();
                String nombreComunidad;
                float latitud;
                float longitud;
                PreparedStatement statement = connection.prepareStatement(CONSULTAR_TODAS_COMUNIDADES);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    nombreComunidad = resultSet.getString("nombre");
                    latitud = resultSet.getFloat("lat");
                    longitud = resultSet.getFloat("long");
                    comunidadVOList.add(new ComunidadVO(nombreComunidad, latitud, longitud));
                }

                return comunidadVOList;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
