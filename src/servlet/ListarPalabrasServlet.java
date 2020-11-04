package servlet;


import model.PalabraVO;
import model.facades.PalabraFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class EliminarPalabraServlet
 */
@WebServlet(description = "Servlet de listado de palabras",
        urlPatterns = { "/listarPalabrasServlet" })
public class ListarPalabrasServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    private static final String URL_LISTA = "";
    private static final String PARAM_LISTA = "listaPalabras";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarPalabrasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PalabraFacade dao = new PalabraFacade();

            List<PalabraVO> lista = dao.consultarPalabras();

            request.setAttribute(PARAM_LISTA, lista);

            request.getRequestDispatcher(URL_LISTA).forward(request, response);
        } catch (Throwable theException) {

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
