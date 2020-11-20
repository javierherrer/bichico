package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(description = "Servlet de cierre de sesión",
        urlPatterns = { "/loginpage/adminpanel/logout" })
public class LogOutServlet extends HttpServlet {
    private static final String ADMIN = "admin";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOutServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
          request.getSession().removeAttribute(ADMIN);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        doGet(request, response);
    }

}