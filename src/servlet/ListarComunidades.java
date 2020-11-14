package servlet;


import model.ComunidadVO;
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
import java.util.List;

/**
 * Servlet implementation class EliminarPalabraServlet
 */
@WebServlet(description = "Servlet de listado de palabras",
        urlPatterns = { "/listarcomunidades" })
public class ListarComunidades extends HttpServlet{
    private static final long serialVersionUID = 1L;

    private static final String COMUNIDAD = "comunidad";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarComunidades() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
      //  if (request.getParameter(COMUNIDAD) != null){

            List<ComunidadVO> comunidadVO  = ComunidadFacade.listarTodas();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            PrintWriter out = response.getWriter();
//            JSONObject obj = new JSONObject();
            JSONArray comunidades = new JSONArray();
            JSONObject tmp;
            System.out.println(comunidadVO.size());
            for (ComunidadVO comunidadVO1: comunidadVO) {
                tmp = new JSONObject();
                tmp.put("nombre", comunidadVO1.getNombre());
                comunidades.add(tmp);
            }
//            obj.put(comunidades);
            System.out.println(comunidades.toString());
            out.write(comunidades.toString());
            out.flush();
            out.close();
       // }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
