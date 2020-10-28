package controller;

import java.sql.Connection;

/**
 * Clase encargada de la conexión con la base de datos
 */
public class ConnectionController {
    public static final String REMOTA = "remota";
    public static final String TOMCAT_LOCAL = "tomcat";

    private static DatabaseConnection databaseConnection = PoolTomcatConnection.instancia();

    private static void setConnectionFacade(DatabaseConnection c){
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

    /**
     * Libera la conexión a la base de datos
     * @param connection
     */
    public static void releaseConnection(Connection connection) {
        if (connection != null){
            try {
                databaseConnection.releaseConnection(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Permite cambiar la conexión utilizada
     * Pueden ser: ConnectionController.REMOTA o ConnectionController.TOMCAT_LOCAL
     * @param database
     */
    public static void changeConnection(String database){
        switch (database){
            case REMOTA:
                setConnectionFacade(PostgreSQLConnection.instancia());
                break;
            case TOMCAT_LOCAL:
                setConnectionFacade(PoolTomcatConnection.instancia());
                break;
        }
    }
}
