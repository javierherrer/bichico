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

    private static final String CONSULTA_FACTOR =
            "SELECT * FROM Bichico.factor f WHERE f.id_region=?";

    public static FactorVO obtenerFactor(RegionVO regionVO){
        FactorVO factorVO = null;
        Connection connection;
        try {
            connection = ConnectionController.getConnection();
            if (connection == null){
                return null;
            }
            PreparedStatement statement = connection.prepareStatement(CONSULTA_FACTOR);
            statement.setInt(1,regionVO.getId());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                factorVO = new FactorVO(
                        resultSet.getDate(FECHA),
                        resultSet.getFloat(VALOR),
                        resultSet.getInt(ID_REGION)
                );
            }
            statement.close();
            resultSet.close();
            ConnectionController.releaseConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return factorVO;
    }
}
