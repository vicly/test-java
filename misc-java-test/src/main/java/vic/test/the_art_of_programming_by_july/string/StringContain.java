package vic.test.the_art_of_programming_by_july.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Vic Liu
 */
public class StringContain {


    /*
     * abcd, da => true
     * abcd, ae => false, since "e"
     */


    public static boolean containInAnyOrder_HashSet(String str1, String str2) {
        if (str1.length() < str2.length()) throw new IllegalArgumentException("chars1.length must >= chars2.length");
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        return doContainInAnyOrder_HashSet(chars1, chars2);
    }

    /**
     * time: O(m + n)
     */
    private static boolean doContainInAnyOrder_HashSet(char[] chars1, char[] chars2) {
        Set<Character> characters = new HashSet<>();
        for (int i = 0; i < chars1.length; i++) {
            characters.add(chars1[i]);
        }

        for (int i = 0; i < chars2.length; i++) {
            if (!characters.contains(chars2[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean containInAnyOrder_BitMark(String str1, String str2) {
        if (str1.length() < str2.length()) throw new IllegalArgumentException("chars1.length must >= chars2.length");
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        return doContainInAnyOrder_BitMark(chars1, chars2);
    }

    /**
     * time: O(m + n)
     *
     * Assume all char are between 'A' and 'Z', so to use 26-bits, each bit map to 'A' to 'Z', represent appearance.
     *
     * If allow ASCII printable chars only, then from 32 to 126 (total 95bits), might use BigInteger
     * BigInteger.shiftLeft(n);
     * BigInteger.and(BigInteger);
     */
    private static boolean doContainInAnyOrder_BitMark(char[] chars1, char[] chars2) {
        int hash = 0; // 32-bits, each bit 0
        for (int i = 0; i < chars1.length; i++) {
            hash |= (1 << (chars1[i] - 'A'));
        }
        // given "ADB" => 00000000 00000000 00000000 00001011 => D B A appears

        for (int i = 0; i < chars2.length; i++) {
            //   00000000 00000000 00000000 00001011   given
            // &
            //   00000000 00000000 00000000 00010000   for 'E'
            // = 00000000 00000000 00000000 00000000   "0", so not exist

            if ( (hash & (1 << (chars2[i] - 'A'))) == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * true, if str1 and str2 has same length and same chars (regardless order)
     *
     * "abc", "bca" => true
     * "abc", "bcd" => false, since "d"
     * "abc", "abcc" => false, since extra "c"
     *
     * How to do it?
     *
     * 1. sort chars1
     * 2. for each string
     * 2.1 sort it
     * 2.2 return false if any char not equal
     * 2.3 return true otherwise
     *
     *
     * time:
     *    O(m log m) + O(n * (O(m log m) + O(m)))
     *
     *    m: chars1.length
     *    n: string with same length
     *
     * 1. sort chars1: O(m log m)
     * 2. for each string with same length: O(sort + scan)
     *      sort: O(m log m)
     *      scan: O(m)
     */
    public static List<String> findBrotherWord(String str1, List<String> strings) {
        char[] chars1 = str1.toCharArray();
        Arrays.sort(chars1);

        return strings
                .stream()
                .filter(s -> isBrotherWord(chars1, s.toCharArray()))
                .collect(Collectors.toList());
    }

    private static boolean isBrotherWord(char[] chars1, char[] chars2) {
        if (chars1.length != chars2.length) return false;
        Arrays.sort(chars2);

        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void testContainInAnyOrder_HashSet() {
        assertTrue(containInAnyOrder_HashSet("ABCDEabc#A", "B#"));
        assertTrue(containInAnyOrder_HashSet("ABCDEabc#SD", "BEa"));
        assertFalse(containInAnyOrder_HashSet("ABCDEabc$%^", "BX"));
    }

    @Test
    public void testContainInAnyOrder_BitMark() {
        assertTrue(containInAnyOrder_BitMark("ABCDE", "B"));
        assertTrue(containInAnyOrder_BitMark("ABCDE", "BE"));
        assertFalse(containInAnyOrder_BitMark("ABCDE", "BX"));
    }

    @Test
    public void testFindBrotherWord() {
        assertThat(
                findBrotherWord("abc", newArrayList("abc", "abc", "cba", "bac", "abcc", "abd")),
                contains("abc", "abc", "cba", "bac"));

    }


}
