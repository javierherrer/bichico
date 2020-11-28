package servlet;


import model.AdminVO;
import model.PalabraVO;
import model.facades.PalabraFacade;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * Servlet implementation class ListarPalabrasServlet
 */
@WebServlet(description = "Servlet de listado de palabras",
        urlPatterns = { "/loginpage/adminpanel/listarpalabras" })
public class ListarPalabrasServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarPalabrasServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PalabraFacade dao = new PalabraFacade();

            List<PalabraVO> lista = dao.consultarPalabras();
            JSONObject obj = new JSONObject();
            JSONArray list = new JSONArray();
            JSONObject palabra;
            //System.out.println("aaaaa" + request.getSession().getAttribute("admin"));

            if (request.getSession().getAttribute("admin") == null) {
                obj.put("error", "true");
            } else {
                AdminVO admin = (AdminVO) request.getSession().getAttribute("admin");

                obj.put("error", "false");
                obj.put("nombre", admin.getNombre());

                for (int i = 0; i < lista.size(); i++) {
                    palabra = new JSONObject();
                    palabra.put("nombre", lista.get(i).getPalabra());
                    palabra.put("importancia", lista.get(i).getImportancia());
                    list.add(palabra);
                }
                obj.put("palabras", list);
            }

		    StringWriter out = new StringWriter();
            obj.writeJSONString(out);

	        String jsonText = out.toString();

	        response.setContentType("application/json");
    		response.setCharacterEncoding("UTF-8");
    		//System.out.println(jsonText);
    		response.getWriter().write(jsonText);
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
