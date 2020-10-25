package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.AdminVO;
import model.ComunidadVO;
import model.FactorVO;
import model.MensajeVO;
import model.PalabraVO;
import model.RegionVO;
import model.facades.AdminFacade;
import model.facades.FactorFacade;
import model.facades.MensajeFacade;
import model.facades.PalabraFacade;
import model.facades.RegionFacade;



/**
 * Servlet implementation class Test
 */
@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MensajeFacade facade = new MensajeFacade();
		//UserVO user = facade.getUser("telleria@unizar.es");
		facade.mostrarMensajes();
		AdminFacade adminFacade = new AdminFacade();
		AdminVO adminPrueba = new AdminVO(1,"juan","1234");
		boolean a = adminFacade.validateAdmin(adminPrueba);
		System.out.println();
		//System.out.println("username = " + user.getUserName());
		//System.out.println("password = " + user.getPassword());
		MensajeFacade f = new MensajeFacade();
		MensajeVO m = new MensajeVO("juan","esta web es una basura","juanico11@hotmail.com");
		f.enviarMensaje(m);
		//request.setAttribute("user", user);
		RegionVO rv =  new RegionVO(2,512, "aa","aa", 2, 1);
		FactorFacade facade2 = new FactorFacade();
		FactorVO vo = facade2.obtenerFactor(rv);
		System.out.println(vo.toString());
		response.sendRedirect("index.html");
		PalabraFacade pf = new PalabraFacade();
		PalabraVO p = new PalabraVO("aaaaaaaa", 5);
		pf.insertarPalabra(p);
		pf.eliminarPalabra("sada");
		ArrayList<String> g = (ArrayList<String>) pf.consultarPalabras();
		for (int i = 0; i < g.size(); i++) {
			System.out.println(g.get(i));
		}
		
		RegionFacade rf = new RegionFacade();
		ComunidadVO cv = new ComunidadVO(nombre, latitud, longitud)
		rf.obtenerRegiones(comunidadVO);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}