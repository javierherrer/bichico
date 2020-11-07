package servlet;


import model.PalabraVO;
import model.facades.PalabraFacade;
import model.facades.RegionFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * Servlet implementation class EliminarPalabraServlet
 */
@WebServlet(description = "Servlet de listado de palabras",
        urlPatterns = { "/adminPanel/listarPalabrasServlet" })
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
            JSONObject obj = new JSONObject();
            JSONArray list = new JSONArray();
            JSONObject obj1;
            for (int i = 0; i < lista.size(); i++) {
            	obj1 = new JSONObject(); 
    			obj1.put("nombre",lista.get(i).getPalabra());
    			obj1.put("importancia",lista.get(i).getImportancia());
    			list.add(obj1);
			}
           
            obj.put("palabras",list);
			
		      /*
				obj.put("nombre",nombre);
		      obj.put("long",longitud);
		      obj.put("lat",latitud);
		      
		}
		
		
	
		 */
		 StringWriter out = new StringWriter();
	      try {
			obj.writeJSONString(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      String jsonText = out.toString();
	     // System.out.print(jsonText);
	      //System.out.println("*********");
	     
           
            /*
            request.setAttribute(PARAM_LISTA, lista);
            request.getRequestDispatcher(URL_LISTA).forward(request, response);
            */
            response.setContentType("text/plain");
    		response.setCharacterEncoding("UTF-8");
    		//poner el objeto completo en 
    		response.getWriter().write(jsonText);
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
