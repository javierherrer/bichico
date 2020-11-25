package servlet;


import model.ComunidadVO;
import model.FactorVO;
import model.RegionVO;
import model.facades.ComunidadFacade;
import model.facades.FactorFacade;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ComunidadVO comunidadVO  = ComunidadFacade.leerComunidad(request.getParameter(COMUNIDAD));
            List<RegionVO> regionVOS = comunidadVO.getRegiones();

            PrintWriter out = response.getWriter();
            // Imprimir comunidad

            JSONArray jsonArray = new JSONArray();
            JSONObject datosregion;
            FactorVO factorVO;

            for (RegionVO region : regionVOS) {

                datosregion = new JSONObject();
                factorVO = FactorFacade.obtenerFactor(region);
                datosregion.put("region", region.toJSON());
                datosregion.put("factor", factorVO.toJSON());
                jsonArray.add(datosregion);

            }

            out.write(jsonArray.toJSONString());

            out.flush();
            out.close();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
