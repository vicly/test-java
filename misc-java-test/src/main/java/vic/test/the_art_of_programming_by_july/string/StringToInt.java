package vic.test.the_art_of_programming_by_july.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Vic Liu
 */
public class StringToInt {

    public static int toInt(String string) {
        // Cannot use builtin function
        // Integer.valueOf(string);
        return toInt(string.toCharArray());
    }

    private static int toInt_basic(char[] chars) {
        // give "123"
        // 1 round: n = 1
        // 2 round: n = 12 = 1*10 + 2;
        // 3 round: n = 123 = 12*10 + 3;

        int n = 0;
        for (int i = 0; i < chars.length; i++) {
            int c = chars[i] - '0';
            n = n * 10 + c;
        }
        return n;
    }

    private static int toInt(char[] chars) {
        // give "123"
        // 1 round: n = 1
        // 2 round: n = 12 = 1*10 + 2;
        // 3 round: n = 123 = 12*10 + 3;

        int n = 0;
        boolean positive = true;
        for (int i = 0; i < chars.length; i++) {
            if (i == 0 && chars[i] == '-') {
                positive = false;
            }

            if (chars[i] == ' ') {
                continue;
            }

            int c = chars[i] - '0';
            //
            // if current N > 1/10 max/min val, then next round (*10) MUST over flow,
            // so just return MAX or MIN
            //
            if (positive && (n > MAX_VALUE / 10 || (n == MAX_VALUE/10 && c > MAX_VALUE % 10))) {
                return MAX_VALUE;
            } else if (!positive && (n > MIN_VALUE / 10 || (n == MIN_VALUE / 10 && c > MIN_VALUE % 10)) ) {
                return MIN_VALUE;
            } else {
                n = n * 10 + c;
            }
        }
        return positive ? n : -n;
    }

    @Test
    public void testToInt() {
        assertThat(toInt("1"), equalTo(1));
        assertThat(toInt("123"), equalTo(123));
        assertThat(toInt("1234567890"), equalTo(1234567890));
        assertThat(toInt("001"), equalTo(1));
    }


}
