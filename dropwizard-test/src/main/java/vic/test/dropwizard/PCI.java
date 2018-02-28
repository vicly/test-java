package vic.test.dropwizard;

import java.util.regex.Pattern;

/**
 * @author Vic Liu
 */
public final class PCI {
    private static final Pattern CVV = Pattern.compile("(\"csv\"\\s*:\\s*)\".*?\"");

    private PCI() {
    }

    public static String mask(String str) {
        return str == null ? null : CVV.matcher(str).replaceAll("$1\"***\"");
    }
}
