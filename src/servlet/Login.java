package servlet;

import model.AdminVO;
import model.facades.AdminFacade;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;

@WebServlet(description = "Servlet de autenticación del usuario",
        urlPatterns = { "/loginpage/login" })
public class Login extends HttpServlet {

    private static final String LOGIN = "inputEmail";
    private static final String URL_LOGIN = "/login";
    private static final String PASSWORD = "password";
    private static final String URL_LOGGED = "/adminpanel";
    private static final String MENSAJE_ERROR = "Error en el login";
    private static final String ERROR_LOGIN = "error";
    private static final String ADMIN = "admin";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        String id = (String) request.getSession().getAttribute(LOGIN);
        String pass = hashPassword((String) request.getSession().getAttribute(PASSWORD));
        String error = "";
        // TODO: 20/11/20 HASH PASS

        if (id == null || pass == null || pass.equals("") || id.equals("")){
            error = "Campos sin rellenar";

        } else {
            AdminVO adminVO = new AdminVO(id, pass);
            if (request.getAttribute(ADMIN) != null || AdminFacade.validateAdmin(adminVO)){

                adminVO.setHashedPass("");
                request.getSession().setAttribute(ADMIN, adminVO);


            } else {

                error = "Contraseña incorrecta";

            }
        }
        sendResponse(response, createJSONResponse(error));
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        doGet(request, response);
    }

    private static void sendResponse(HttpServletResponse response, String json){

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static String createJSONResponse(String error){

        JSONObject object = new JSONObject();
        object.put(ERROR_LOGIN, error);
        StringWriter sw = new StringWriter();
        try {
            object.writeJSONString(sw);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sw.toString();

    }

    /**
     * Returns a hashed pass
     * @param pass
     * @return
     */
    private static String hashPassword(String pass){
        return pass;
    }
}
