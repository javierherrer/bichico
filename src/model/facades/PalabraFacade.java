package model.facades;

import controller.ConnectionController;
import model.PalabraVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PalabraFacade {

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
        String SQL = "INSERT INTO Bichico.palabra (nombre, importancia) " +
                "VALUES (?,?)";

        String nombre ="";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, palabra.getPalabra());
            pstmt.setFloat(2, palabra.getImportancia());

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
        }

        return nombre;
    }

    /**
     * Consulta palabras
     *
     * @return una lista de palabras
     */
    public List<String> consultarPalabras() {
        String SQL = "SELECT p.nombre FROM Bichico.palabra p";

        List<String> listaPalabras = new ArrayList<String>();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL);
             ResultSet consultarRs = pstmt.executeQuery()) {

            //Leemos los registros
            while (consultarRs.next()) {
                String palabra = consultarRs.getString("nombre");
                listaPalabras.add(palabra);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
        String SQL = "DELETE FROM bichico.palabra " +
                "WHERE nombre = ?";

        int affectedrows = 0;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, nombre);

            affectedrows = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return affectedrows;
    }


}
