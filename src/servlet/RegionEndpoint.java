package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(description = "Servlet de eliminacion de palabra",
        urlPatterns = { "/listarRegiones" })
public class RegionEndpoint extends HttpServlet {
    // LAt Lon y factor

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                          IOException {
        // TODO: 4/11/20 Obtener el parametro de nombre de region
        String comunidad = "";
            if (comunidad.equals("")){
                listar();
            } else {
                listar(comunidad);
            }
    }

    /**
     * Lista todas las regiones de ESPAÑA!
     */
    private void listar() {

    }

    /**
     * Lista las ciudades de todas las regiones
     * @param region
     */
    private void listar(String region) {

    }

    /**
     * Constr
     */


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
