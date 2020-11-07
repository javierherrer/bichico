package model.facades;

import controller.ConnectionController;
import model.ComunidadVO;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ComunidadFacade {


    private static final String  CONSULTA_COMUNIDAD
            = "";

    /**
     * Devuelve una comunidadVO
     * @return
     */
    public ComunidadVO leerComunidad(){

        Connection connection = null;
        ComunidadVO comunidadVO = null;

        try {
            connection = ConnectionController.getConnection();
            if (connection == null){
                return null;
            }
            PreparedStatement statement = connection.prepareStatement(CONSULTA_COMUNIDAD);



        } catch (Exception e){

        }
        return null;
    }
}
