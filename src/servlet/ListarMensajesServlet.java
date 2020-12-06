package servlet;


import model.MensajeVO;
import model.facades.MensajeFacade;
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
 * Servlet implementation class ListarMensajesServlet
 */
@WebServlet(description = "Servlet de listado de mensajes",
        urlPatterns = { "/loginpage/adminpanel/listarmensajes" })
public class ListarMensajesServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarMensajesServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            MensajeFacade dao = new MensajeFacade();

            List<MensajeVO> lista = dao.consultarMensajes();
            JSONObject obj = new JSONObject();
            JSONArray list = new JSONArray();
            JSONObject mensaje;

            if (request.getSession().getAttribute("admin") == null) {
                obj.put("error", "true");
            } else {
                obj.put("error", "false");

                for (int i = 0; i < lista.size(); i++) {
                    mensaje = new JSONObject();
                    mensaje.put("id", lista.get(i).getId());
                    mensaje.put("emisor",lista.get(i).getEmisor());
                    mensaje.put("email", lista.get(i).getEmail());
                    mensaje.put("contenido", lista.get(i).getContenido());

                    list.add(mensaje);
                }
                obj.put("mensajes",list);
            }

            StringWriter out = new StringWriter();
            obj.writeJSONString(out);

            String jsonText = out.toString();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            System.out.println(jsonText);
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

