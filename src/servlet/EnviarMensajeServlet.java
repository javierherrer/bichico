package servlet;


import model.MensajeVO;
import model.facades.MensajeFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.lang.Float.parseFloat;

/**
 * Servlet implementation class EnviarMensajeServlet
 */
@WebServlet(description = "Servlet de envío de mensajes",
        urlPatterns = { "/enviarMensajeServlet" })
public class EnviarMensajeServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    private static final String URL_ADMIN = "bichico";
    private static final String PARAM_EMISOR = "emisor";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_CONTENIDO = "contenido";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnviarMensajeServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MensajeFacade dao = new MensajeFacade();

        if (request.getParameter() == null) {
            request.getRequestDispatcher(URL_ADMIN).forward(request, response);
        } else {
            PalabraVO palabra = new PalabraVO(request.getParameter(PARAM_PALABRA),
                    parseFloat(request.getParameter(PARAM_IMPORTANCIA)));
            String id = dao.insertarPalabra(palabra);
            /* No consideramos errores
            if (id == null || id.equals("")) {
                request.setAttribute("error", "invalid word");
                request.getRequestDispatcher(URL_ADMIN).forward(request, response);
            } else {
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
