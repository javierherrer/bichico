package servlet;


import model.ComunidadVO;
import model.facades.ComunidadFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class EliminarPalabraServlet
 */
@WebServlet(description = "Servlet de listado de palabras",
        urlPatterns = { "/listarregiones" })
public class ListarRegiones extends HttpServlet{
    private static final long serialVersionUID = 1L;

    private static final String COMUNIDAD = "comunidad";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarRegiones() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	if (request.getParameter(COMUNIDAD) != null){
    		System.out.println(request.getParameter(COMUNIDAD));
            ComunidadVO comunidadVO  = ComunidadFacade.leerComunidad(request.getParameter(COMUNIDAD));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            PrintWriter out = response.getWriter();
           
            out.write(comunidadVO.toJSON().toString());
            out.flush();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
