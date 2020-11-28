package model.facades;

import controller.ConnectionController;
import model.FactorVO;
import model.RegionVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FactorFacade {

    private static final String FECHA = "fecha";
    private static final String  VALOR = "valor";
    private static final String  ID_REGION = "id_region";

    private final static String INSERTAR_FACTOR =
            "INSERT INTO Bichico.factor (fecha, id_region, valor) " +
                    "VALUES (?,?,?)";

    private static final String CONSULTA_FACTOR =
            "SELECT * FROM Bichico.factor f WHERE f.id_region=?";

    /**
     * Inserta un factor
     *
     * @param factor
     * @return
     */
    public static void insertarFactor(FactorVO factor) {
        Connection conn = null;
        try {
            conn = ConnectionController.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(INSERTAR_FACTOR);

            pstmt.setDate(1, factor.getFecha());
            pstmt.setInt(2, factor.getId_region());
            pstmt.setFloat(3, factor.getValor());
           // System.out.println(pstmt);

            pstmt.executeUpdate();

            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionController.releaseConnection(conn);
        }
    }

    public static FactorVO obtenerFactor(RegionVO regionVO){
        FactorVO factorVO = null;
        Connection connection = null;
        try {
            connection = ConnectionController.getConnection();
            if (connection == null){
                return null;
            }
            PreparedStatement statement = connection.prepareStatement(CONSULTA_FACTOR);
            statement.setInt(1,regionVO.getId());
            //System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                factorVO = new FactorVO(
                        resultSet.getDate(FECHA),
                        (int)resultSet.getFloat(VALOR),
                        resultSet.getInt(ID_REGION)
                );
            }
            statement.close();
            resultSet.close();
            ConnectionController.releaseConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
			ConnectionController.releaseConnection(connection); 
		}
        return factorVO;
    }
}
