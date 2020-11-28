package servlet;


import model.ComunidadVO;
import model.FactorVO;
import model.RegionVO;
import model.facades.ComunidadFacade;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

/**
 * Servlet implementation class EliminarPalabraServlet
 */
@WebServlet(description = "Servlet de listado de regiones de una comunidad con sus factores",
        urlPatterns = { "/listarregiones" })
public class ListarRegionesServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    private static final String PARAM_COMUNIDAD = "id";
    private static final float VALOR_FACTOR = 20;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarRegionesServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ComunidadFacade dao = new ComunidadFacade();


        try {
            if (request.getParameter(PARAM_COMUNIDAD) != null){

                int id = Integer.parseInt(request.getParameter(PARAM_COMUNIDAD));

                Map<RegionVO, FactorVO> lista = dao.obtenerFactorRegiones(id);
                JSONObject obj = new JSONObject();
                JSONArray list = new JSONArray();
                JSONObject regionJson;

                for (Map.Entry<RegionVO, FactorVO> entry : lista.entrySet()) {
                    regionJson = new JSONObject();

                    RegionVO region = entry.getKey();
                    FactorVO factor = entry.getValue();

                    if (factor.getValor() > VALOR_FACTOR) {
                        regionJson.put("nombre", region.getNombre());
                        regionJson.put("latitud", region.getLatitud());
                        regionJson.put("longitud", region.getLongitud());
                        regionJson.put("habitantes", region.getHabitantes());
                        regionJson.put("factor", factor.getValor());
                        list.add(regionJson);
                    }
                }

                obj.put("regiones", list);

                StringWriter out = new StringWriter();
                obj.writeJSONString(out);

                String jsonText = out.toString();


                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                //System.out.println(jsonText);
                response.getWriter().write(jsonText);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
