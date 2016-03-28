package vic.test.spring.security;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 * @author Vic Liu
 */
public class WebLoginModule implements LoginModule {

    private static final String USER = "vic";
    private static final String PASSWORD = "password";

    private CallbackHandler callbackHandler;
    private boolean authSuccess = false;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        println("login");

        Callback[] callbacks = new Callback[3];
        callbacks[0] = new NameCallback("User name:");
        callbacks[1] = new PasswordCallback("Password:", false);

        try {
            this.callbackHandler.handle(callbacks);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
            e.printStackTrace();
        }

        // do authentication
        String name = ((NameCallback) callbacks[0]).getName();
        String password = new String(((PasswordCallback) callbacks[1]).getPassword());
        if (USER.equals(name) && PASSWORD.equals(password)
                ) {
            authSuccess = true;
        } else {
            authSuccess = false;
            throw new FailedLoginException("Authentication is failed");
        }

        return authSuccess;
    }

    @Override
    public boolean commit() throws LoginException {
        println("commit");
        return this.authSuccess;
    }

    @Override
    public boolean abort() throws LoginException {
        println("abort");
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        println("logout");
        return false;
    }

    private void println(String message) {
        System.out.println("WebLoginModule." + message);
    }

}
