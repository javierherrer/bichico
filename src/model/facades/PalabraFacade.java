package model.facades;

import controller.ConnectionController;
import model.PalabraVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PalabraFacade {
    private final static String INSERTAR_PALABRA =
            "INSERT INTO Bichico.palabra (nombre, importancia) " +
            "VALUES (?,?)";

    private final static String CONSULTAR_PALABRAS = "SELECT p.nombre FROM Bichico.palabra p";

    private static final String ELIMINAR_PALABRA = "DELETE FROM bichico.palabra " +
            "WHERE nombre = ?";
    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect(){
        return ConnectionController.getConnection();
    }

    /**
     * Inserta una palabra
     *
     * @param palabra
     * @return
     */
    public String insertarPalabra(PalabraVO palabra) {
        String nombre ="";

        Connection conn = null;
        try {
        	conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(INSERTAR_PALABRA,
            Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, palabra.getPalabra());
            pstmt.setFloat(2, palabra.getImportancia());
            System.out.println(pstmt);
            int filas = pstmt.executeUpdate();
            // comprobar filas afectadas
            if (filas > 0) {
                // devolver el ID
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        nombre = rs.getString(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	ConnectionController.releaseConnection(conn); 
		} 

        return nombre;
    }

    /**
     * Consulta palabras
     *
     * @return una lista de palabras
     */
    public List<String> consultarPalabras() {


        List<String> listaPalabras = new ArrayList<String>();

        Connection conn = null;
        try {
        	conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(CONSULTAR_PALABRAS);
            ResultSet consultarRs = pstmt.executeQuery();

            //Leemos los registros
            while (consultarRs.next()) {
                String palabra = consultarRs.getString("nombre");
                listaPalabras.add(palabra);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
        	ConnectionController.releaseConnection(conn);  
		} 

        return listaPalabras;
    }

    /**
     * Eliminar palabras por nombre
     *
     * @param nombre
     * @return
     */

    public int eliminarPalabra(String nombre) {

        int affectedrows = 0;
        Connection conn = null;
        try {
        	conn = connect();
        	PreparedStatement pstmt = conn.prepareStatement(ELIMINAR_PALABRA);

            pstmt.setString(1, nombre);

            affectedrows = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
        	ConnectionController.releaseConnection(conn); 
		} 
        return affectedrows;
    }


}
