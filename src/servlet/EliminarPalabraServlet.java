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
        urlPatterns = { "/eliminarPalabraServlet" })
public class EliminarPalabraServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    private static final String URL_ADMIN = "admin.jsp";
    private static final String PARAM_PALABRA = "palabra";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarPalabraServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PalabraFacade dao = new PalabraFacade();

        if (request.getParameter(PARAM_PALABRA) == null) {
            request.getRequestDispatcher(URL_ADMIN).forward(request, response);
        } else {
            String id = request.getParameter(PARAM_PALABRA);
            int rows = dao.eliminarPalabra(id);
            /* No consideramos errores
            if (rows < 1) {
                request.getRequestDispatcher(URL_ADMIN).forward(request, response);
            } else {
                request.setAttribute("error", "invalid word");
                request.getRequestDispatcher(URL_ADMIN).forward(request, response);
            }
             */
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
