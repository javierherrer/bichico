import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import controller.ConnectionController;
import model.ComunidadVO;
import model.facades.ComunidadFacade;
import model.facades.RegionFacade;
import utils.DataCleaner;

public class main {

	public static void main(String[] args) throws Exception {
		System.out.println(DataCleaner.clean("Campo de Mirra/Camp de Mirra (el)"));
		// TODO Auto-generated method stub
		//testTomcat();
//		testRemoto();
//		ConnectionController.changeConnection(ConnectionController.REMOTA);
//		ComunidadVO comunidadVO = ComunidadFacade.leerComunidad("Aragón");
//
//		System.out.println(comunidadVO.toJSON().toJSONString());

	}


	private static void testTomcat() {
		Connection conn = null;
		
	
	}

	private static void testRemoto(){
		Connection connection =null;
		try {
			ConnectionController.changeConnection(ConnectionController.REMOTA);
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
