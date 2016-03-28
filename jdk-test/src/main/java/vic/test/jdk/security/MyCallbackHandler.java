package vic.test.jdk.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.ChoiceCallback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 * Collect user input
 *
 * @author Vic Liu
 */
public class MyCallbackHandler implements CallbackHandler {

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

        NameCallback nameCallback = (NameCallback) callbacks[0];
        PasswordCallback passwordCallback = (PasswordCallback) callbacks[1];
        ChoiceCallback choiceCallback = (ChoiceCallback) callbacks[2];

        System.out.println(nameCallback.getPrompt());
        nameCallback.setName(new BufferedReader(new InputStreamReader(System.in)).readLine());

        System.out.println(passwordCallback.getPrompt());
        passwordCallback.setPassword(new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray());

        StringBuilder choicePrompt = new StringBuilder();
        choicePrompt.append(choiceCallback.getPrompt());
        String[] choices = choiceCallback.getChoices();
        for (int i = 0, len = choices.length; i < len; i++) {
            choicePrompt.append(' ').append('(').append(i).append(')').append(choices[i]);
        }
        System.out.println(choicePrompt);

        int selected = Integer.valueOf(
                new BufferedReader(
                        new InputStreamReader(System.in)
                ).readLine()
        );
        choiceCallback.setSelectedIndex(selected);

    }
}
