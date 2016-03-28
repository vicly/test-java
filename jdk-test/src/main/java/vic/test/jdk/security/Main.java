package vic.test.jdk.security;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * @author Vic Liu
 */
public class Main {
    public static void main(String[] args) {
        //
        // Options to set the login config file
        //
        // 1. set programatically
        System.setProperty("java.security.auth.login.config", "/Users/vic.liu/Git/test-java/jdk-test/src/main/resources/myjaas.config");
        // 2. -Dava.security.auth.login.config=file:xxxx
        // 3. $java_home/jre/lib/security/java.security
        //    login.config.url.1=file:xxxx


        final String contextName = "MyJaasConfig"; // from myjaas.config
        LoginContext loginContext = null;
        try {
            loginContext = new LoginContext(contextName, new MyCallbackHandler());
        } catch (LoginException e) {
            e.printStackTrace();
            System.exit(0);
        }

        try {
            loginContext.login();
            System.out.printf("\n\n>> login OK");
            System.exit(0);
        } catch (LoginException e) {
            System.out.printf("\n\n>> login failed");
            e.printStackTrace();
        }
    }
}
