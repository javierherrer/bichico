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
		
	
		RegionFacade rf = new RegionFacade();
		ComunidadVO cv = new ComunidadVO("Valencia",1,2);
		ArrayList<RegionVO> c = (ArrayList<RegionVO>) rf.obtenerRegiones(cv);
		for (int i = 0; i < c.size(); i++) {
			System.out.println(c.get(i));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}