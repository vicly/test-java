package vic.test.spring.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vic Liu
 */
public class AuthenticationServlet extends HttpServlet {

    public AuthenticationServlet() {
        super();

        System.setProperty("java.security.auth.login.config",
                "/Users/vic.liu/Git/test-java/jdk-security-web-test/src/main/resources/mywebjaas.config");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // get credential from request parameters
        String user = req.getParameter("user");
        String password = req.getParameter("password");
        if (user != null && password != null) {
            // expose to LoginModule
            CallbackHandler callbackHandler = callbacks -> {
                NameCallback nameCallback = (NameCallback) callbacks[0];
                PasswordCallback passwordCallback = (PasswordCallback) callbacks[1];
                nameCallback.setName(user);
                passwordCallback.setPassword(password.toCharArray());
            };
            // do login
            try {
                LoginContext loginContext = new LoginContext("MyWebJaasConfig", callbackHandler);
                loginContext.login();
                out.print("authentication success!!");
            } catch (LoginException e) {
                out.print("authentication failed - " + e.getMessage());
            }
        } else {
            out.println("user and password are both required, please provide");
        }
    }
}
