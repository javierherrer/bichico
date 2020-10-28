package servlet;


import model.PalabraVO;
import model.facades.PalabraFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Float.parseFloat;

/**
 * Servlet implementation class InsertarPalabraServlet
 */
@WebServlet(description = "Servlet de inserción de palabra",
        urlPatterns = { "/insertarPalabraServlet" })
public class InsertarPalabraServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    private static final String URL_ADMIN = "admin.jsp";
    private static final String PARAM_PALABRA = "palabra";
    private static final String PARAM_IMPORTANCIA = "importancia";


    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarPalabraServlet() {
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
            PalabraVO palabra = new PalabraVO(request.getParameter(PARAM_PALABRA),
                    parseFloat(request.getParameter(PARAM_IMPORTANCIA)));
            String id = dao.insertarPalabra(palabra);
            if (id == null || id.equals("")) {
                request.setAttribute("error", "invalid word");
                request.getRequestDispatcher(URL_ADMIN).forward(request, response);
            } else {
                request.getRequestDispatcher(URL_ADMIN).forward(request, response);
            }
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
