package controller;

import java.sql.Connection;

public class ConnectionController {
    private static ConnectionFacade connectionFacade = PoolConnectionManager.instancia();

    public static void setConnectionFacade(ConnectionFacade c){
        connectionFacade = c;
    }

    /**
     * Obtiene una conexión de la base de datos
     * @return
     */
    public static Connection getConnection(){
        try {
            return connectionFacade.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void releaseConnection(Connection connection) {
        try {
            connectionFacade.releaseConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
