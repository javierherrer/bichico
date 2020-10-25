package controller;

import java.sql.Connection;

public class ConnectionController {
    private static DatabaseConnection databaseConnection = PoolDatabaseConnectionManager.instancia();

    public static void setConnectionFacade(DatabaseConnection c){
        databaseConnection = c;
    }

    /**
     * Obtiene una conexión de la base de datos
     * @return
     */
    public static Connection getConnection(){
        try {
            return databaseConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void releaseConnection(Connection connection) {
        if (connection != null){
            try {
                databaseConnection.releaseConnection(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
