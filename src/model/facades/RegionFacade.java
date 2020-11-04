package model.facades;

import controller.ConnectionController;
import model.ComunidadVO;
import model.RegionVO;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;



/**
 * Representa una Region
 */
public class RegionFacade {
    private static final String ID = "id";
    private static final String HABITANTES = "habitantes";
    private static final String NOMBRE = "nombre";
    private static final String LATITUD = "lat";
    private static final String LONGITUD = "long";
    private static final String COMUNIDAD = "id_com";

    private final static String CONSULTA_REGIONES =
    		"SELECT * FROM Bichico.region r where r.id_com = (select c.id from Bichico.comunidad c where c.nombre = ?)";

    private final static String CONSULTA_LATITUD =
            "SELECT r.lat " +
            "FROM Bichico.region r " +
            "WHERE r.id = ?";

    private final static String CONSULTA_LONGITUD =
            "SELECT r.long " +
            "FROM Bichico.region r " +
            "WHERE r.id = ?";

    /**
     * Obtiene una lista de regiones en la base de datos
     * @return List<RegionVO>
     */
    public List<RegionVO> obtenerRegiones(ComunidadVO comunidadVO){
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
            ConnectionController.releaseConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRegiones;
    }
    
    //prueba
    public static String obtenerJSON() {
    	Connection conn;
    	
    	String jsonText = null;
  
		// Abrimos la conexión e inicializamos los parámetros 
    	try {
    	JSONObject obj = new JSONObject();
    	conn = ConnectionController.getConnection();
		PreparedStatement ps = conn.prepareStatement("select  * from bichico.region r where r.id_com  = 2;");
		ResultSet rset;
	
			rset = ps.executeQuery();
			
		RegionVO rs = null;
		
	    

	    JSONArray list = new JSONArray();
	   
	 
	    JSONObject obj1;
		while(rset.next()) {
			String nombre = rset.getString("nombre");
			float longitud = rset.getFloat("long");
			float latitud = rset.getFloat("lat");
			obj1 = new JSONObject(); 
			obj1.put("nombre",nombre);
			obj1.put("long",longitud);
			obj1.put("lat",latitud);
			list.add(obj1);
	    
		}
		obj.put("regiones",list);
			
		      /*
				obj.put("nombre",nombre);
		      obj.put("long",longitud);
		      obj.put("lat",latitud);
		      
		}
		
		
	
		 */
		 StringWriter out = new StringWriter();
	      try {
			obj.writeJSONString(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	       jsonText = out.toString();
	      System.out.print(jsonText);
	     
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    	return jsonText;
    }

    /**
     * Obtiene la longitud de una determinada region
     * @param regionVO
     * @return
     */
    public float obtenerLongitud(RegionVO regionVO){
        Connection connection;
        float longitud = 0;
        try {
            connection = ConnectionController.getConnection();
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
            ConnectionController.releaseConnection(connection);
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
            connection = ConnectionController.getConnection();
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
            ConnectionController.releaseConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return latitud;
    }
}
