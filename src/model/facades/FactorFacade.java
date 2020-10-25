package model.facades;

import controller.PoolConnectionManager;
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

    public FactorVO obtenerFactor(RegionVO regionVO){
        FactorVO factorVO = null;
        Connection connection = null;
        try {
            connection = PoolConnectionManager.getConnection();
            if (connection == null){
                return null;
            }
            PreparedStatement statement = connection.prepareStatement(CONSULTA_FACTOR);
            statement.setInt(1,regionVO.getId());
            System.out.println(statement);
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
            PoolConnectionManager.releaseConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }finally {
			PoolConnectionManager.releaseConnection(connection); 
		}
        return factorVO;
    }
}
