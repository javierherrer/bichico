package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdminVO;
import model.MensajeVO;
import model.facades.AdminFacade;
import model.facades.MensajeFacade;



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
		MensajeVO m = new MensajeVO("juan","juanico11@hotmail.com","esta web es una basura");
		f.enviarMensaje(m);
		//request.setAttribute("user", user);
		
		response.sendRedirect("index.html");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}