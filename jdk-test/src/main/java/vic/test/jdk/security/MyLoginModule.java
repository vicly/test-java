package vic.test.jdk.security;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.ChoiceCallback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 * @author Vic Liu
 */
public class MyLoginModule implements LoginModule {

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
        println("MyLoginModule() - login");

        Callback[] callbacks = new Callback[3];
        callbacks[0] = new NameCallback("User name:");
        callbacks[1] = new PasswordCallback("Password:", false);
        callbacks[2] = new ChoiceCallback("User type:", new String[]{"facebook", "twitter"}, 0, false);

        // collect user input
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
        int type = ((ChoiceCallback) callbacks[2]).getSelectedIndexes()[0];
        if (USER.equals(name) &&
                PASSWORD.equals(password)
                && 0 == type
                ) {
            authSuccess = true;
        } else {
            throw new FailedLoginException("Authentication is failed");
        }

        return authSuccess;
    }

    @Override
    public boolean commit() throws LoginException {
        println("MyLoginModule() - commit");
        return this.authSuccess;
    }

    @Override
    public boolean abort() throws LoginException {
        println("MyLoginModule() - abort");
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        println("MyLoginModule() - logout");
        return false;
    }

    private void println(String message) {
        System.out.println(message);
    }

}
