import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import controller.ConnectionController;
import model.facades.RegionFacade;

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		
		try {
			System.out.println(RegionFacade.obtenerJSON());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionController.releaseConnection(conn);
		}
	}

}
