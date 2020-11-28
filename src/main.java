import controller.ConnectionController;
import encriptar.Encriptador;
import utils.DataCleaner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class main {

	public static void main(String[] args) throws Exception {
		System.out.println(DataCleaner.clean("Campo de Mirra/Camp de Mirra (el)"));
		//testTomcat();
//		testRemoto();
//		ConnectionController.changeConnection(ConnectionController.REMOTA);
//		ComunidadVO comunidadVO = ComunidadFacade.leerComunidad("Aragón");
//
//		System.out.println(comunidadVO.toJSON().toJSONString());
		
		String c = "1234";
		String c1 = "1234";
		String c2 = "1234";
		String c3 = "1234";
		
		System.out.println(Encriptador.encriptar(c));
		System.out.println(Encriptador.encriptar(c1));
		System.out.println(Encriptador.encriptar(c2));
		System.out.println(Encriptador.encriptar(c3));
		
		
	}


	private static void testTomcat() {
		Connection conn = null;
		
	
	}

	private static void testRemoto(){
		Connection connection =null;
		try {
			//ConnectionController.changeConnection(ConnectionController.REMOTA);
			connection = ConnectionController.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT t.*" +
					"                 FROM bichico.admin t" +
					"                 LIMIT 501");
			ResultSet rset = ps.executeQuery();
			while(rset.next()) {
				System.out.println(rset.getString("nombre"));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
