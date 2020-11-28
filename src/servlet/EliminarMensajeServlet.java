package servlet;


import model.facades.MensajeFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class EliminarMensajeServlet
 */
@WebServlet(description = "Servlet de eliminacion de mensaje",
        urlPatterns = { "/loginpage/adminpanel/eliminarmensaje" })
public class EliminarMensajeServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    private static final String PARAM_ID = "id";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarMensajeServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MensajeFacade dao = new MensajeFacade();

        System.out.println("paso");
        if (request.getParameter(PARAM_ID) != null) {
            System.out.println("entro if");
            int id = Integer.parseInt(request.getParameter(PARAM_ID));
            int rows = dao.eliminarMensaje(id);
            System.out.println("entro eliminar");
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

        doGet(request, response);
    }
}
