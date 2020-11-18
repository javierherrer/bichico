package servlet;


import model.PalabraVO;
import model.facades.PalabraFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import static java.lang.Float.parseFloat;

/**
 * Servlet implementation class InsertarPalabraServlet
 */
@WebServlet(description = "Servlet de inserción de palabra",
        urlPatterns = { "/adminPanel/insertarPalabraServlet" })
public class InsertarPalabraServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    private static final String PARAM_PALABRA = "nombre";
    private static final String PARAM_IMPORTANCIA = "importancia";


    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarPalabraServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PalabraFacade dao = new PalabraFacade();
      
        if (request.getParameter(PARAM_PALABRA) != null) {
            PalabraVO palabra = new PalabraVO(request.getParameter(PARAM_PALABRA),
                    parseFloat(request.getParameter(PARAM_IMPORTANCIA)));
            dao.insertarPalabra(palabra);
            //No se realiza tratamiento de errores
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
