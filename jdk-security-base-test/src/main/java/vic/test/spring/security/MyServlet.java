package vic.test.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vic Liu
 */
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("MyServlet....");

        System.out.println("getRemoteUser(): " + req.getRemoteUser());
        System.out.println("getAuthType(): " + req.getAuthType());
        System.out.println("getUserPrincipal().getName(): " + req.getUserPrincipal().getName());
        System.out.println("getUserPrincipal().toString(): " + req.getUserPrincipal().toString());
        System.out.println("isUserInRole(\"servletuser\"): " + req.isUserInRole("servletuser"));
        System.out.println("isUserInRole(\"user\"): " + req.isUserInRole("user"));
        System.out.println("isUserInRole(\"invalidrole\"): " + req.isUserInRole("invalidrole"));
    }
}
