package servlet;

import model.AdminVO;
import model.ComunidadVO;
import model.RegionVO;
import model.facades.AdminFacade;
import model.facades.RegionFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(description = "Servlet de autenticación del usuario", urlPatterns = { "/login" })
public class Login extends HttpServlet {

    private static final String LOGIN = "inputEmail";
    private static final String URL_LOGIN = "login.jsp";
    private static final String PASSWORD = "password";
    private static final String URL_LOGGED = "logged.jsp";
    private static final String  MENSAJE_ERROR = "Error en el login";
    private static final String ERROR_LOGIN = "error";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                          IOException {
        String id = (String) request.getSession().getAttribute(LOGIN);
        String pass = (String) request.getSession().getAttribute(PASSWORD);
        if (id == null){
            request.getRequestDispatcher(URL_LOGIN).forward(request, response);
        } else {
            AdminVO adminVO = new AdminVO(id, pass);
            if (AdminFacade.validateAdmin(adminVO)){
                adminVO.setHashedPass("");
                request.getSession().setAttribute("admin", adminVO);
                request.getRequestDispatcher(URL_LOGGED).forward(request, response);
            } else {
                request.setAttribute(ERROR_LOGIN, MENSAJE_ERROR);
                request.getRequestDispatcher(URL_LOGIN).forward(request, response);
            }
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
