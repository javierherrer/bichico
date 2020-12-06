package servlet;


import model.facades.PalabraFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class EliminarPalabraServlet
 */
@WebServlet(description = "Servlet de eliminacion de palabra",
        urlPatterns = { "/loginpage/adminpanel/eliminarpalabra" })
public class EliminarPalabraServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    private static final String PARAM_PALABRA = "palabra";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarPalabraServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PalabraFacade dao = new PalabraFacade();
        if (request.getParameter(PARAM_PALABRA) != null) {
            String id = request.getParameter(PARAM_PALABRA);
            int rows = dao.eliminarPalabra(id);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }
}
