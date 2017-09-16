package vic.test.the_art_of_programming_by_july.string;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Vic Liu
 */
public class StringReverseAndMoveChars {

    // region reverse CHAR by CHAR: abc -> cba, very basic function used by others
    private static String reverse(String str) {
        char[] chars = str.toCharArray();
        reverse(chars, 0, chars.length - 1);
        return new String(chars);
    }

    /**
     * reverse a char array. E.g.  abcd -> dcba
     *
     * time: O(n/2)
     * space: O(1)
     */
    private static void reverse(char[] chars, int from, int to) {
        while (from < to) {
            char t = chars[from];
            chars[from++] = chars[to];
            chars[to--] = t;
        }
    }

    @Test
    public void testReverseString() {
        assertEquals("input is a", "a", reverse("a"));
        assertEquals("input is ab", "ba", reverse("ab"));
        assertEquals("input is a b", "a b", reverse("b a"));
    }
    // endregion

    // region reverse WORD by WORD: hello world => world hello
    public static String reverseStringByWord(String str) {
        char[] chars = str.toCharArray();
        int maxIdx = chars.length - 1;

        // step1: revere whole, so to the correct ordered words, but each chars in word are reversed
        //        e.g. ab cde fgh i => i hgf edc ba
        reverse(chars, 0, maxIdx);

        // step2: handle each word, reverse it back
        int wordFrom = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                if (wordFrom == -1) {
                    continue; // no word found yet, by pass consecutive spaces
                }
                reverse(chars, wordFrom, i - 1);
                wordFrom = -1;
            } else {
                if (wordFrom == -1) {
                    wordFrom = i; // remember index of first non-space char
                }
            }
        }

        if (wordFrom != -1 && wordFrom < maxIdx) {
            reverse(chars, wordFrom, maxIdx);
        }

        return new String(chars);
    }

    @Test
    public void testReverseStringByWord() {
        assertEquals("a", reverseStringByWord("a"));
        assertEquals(" a", reverseStringByWord("a "));
        assertEquals("  a", reverseStringByWord("a  "));
        assertEquals(" a ", reverseStringByWord(" a "));
        assertEquals("  a ", reverseStringByWord(" a  "));
        assertEquals("b a", reverseStringByWord("a b"));
        assertEquals("b  a", reverseStringByWord("a  b"));

        assertEquals("student. a am I", reverseStringByWord("I am a student."));
        assertEquals("I am a student.", reverseStringByWord(reverseStringByWord("I am a student.")));

        assertEquals("     efg    cd   b  a ", reverseStringByWord(" a  b   cd    efg     "));
    }
    // endregion

    // region move chars to end/beginning:  abcef => cefab
    public static String moveCharsToEnd_oneByOne(String str, int numToMove) {
        return new String(moveCharsToEnd_oneByOne(str.toCharArray(), numToMove));
    }

    /**
     * Move the first {@code numToMove} elements from end of the array.
     *
     * time:  O(n)
     * space: O(1)
     */
    private static char[] moveCharsToEnd_oneByOne(char[] chars, int numToMove) {
        if (numToMove > chars.length) throw new IllegalArgumentException("numToMove must <= chars.length");
        if (chars == null) return null;
        if (numToMove % chars.length == 0) return chars;

        // any in [0, chars.length-1]
        int idx = 0;
        char idxVal = chars[idx];
        int counter = 0;
        // O(n) : time cost grows with array size
        while (counter++ < chars.length) {
            // "abcde".length = 5, numToMove = 2
            //   * for idx 1:  1 - 2 = -1 + 5 = 4
            //   * for idx 2:  2 - 2 = 0
            int idxTo = idx - numToMove < 0 ? idx - numToMove + chars.length : idx - numToMove;
            char v = chars[idxTo];
            chars[idxTo] = idxVal;
            idx = idxTo;
            idxVal = v;
        }
        return chars;
    }

    @Test
    public void testMoveCharsToEnd() {
        assertEquals("abcde", moveCharsToEnd_oneByOne("abcde", 0));
        assertEquals("bcdea", moveCharsToEnd_oneByOne("abcde", 1));
        assertEquals("cdeab", moveCharsToEnd_oneByOne("abcde", 2));
        assertEquals("deabc", moveCharsToEnd_oneByOne("abcde", 3));
        assertEquals("eabcd", moveCharsToEnd_oneByOne("abcde", 4));
    }

    public static String moveCharsToEnd_3StepReverse(String str, int numToMove) {
        char[] chars = str.toCharArray();
        moveCharsToEnd_3StepReverse(chars, numToMove);
        return new String(chars);
    }

    /**
     * Three steps reverse.
     *
     * XY => (X^TY^T)^T => YX
     * XY => reverse( reverse(X) reverse(Y) ) => YX
     *
     * see https://github.com/julycoding/The-Art-Of-Programming-By-July/blob/master/ebook/zh/01.01.md
     *
     * Example: given chars "abcdef", to move "ab" to the end, so get "cdefab"
     *
     * chars=XY, X=ab, Y=cdef,
     * step 1. X^T => ba cdef
     * step 2. Y^T => ba fedc
     * step 3. chars^T  => cdef ab
     *
     */
    private static void moveCharsToEnd_3StepReverse(char[] chars, int numToMove) {

        numToMove %= chars.length;
        //
        // 3-steps reverse
        //
        // chars="abcdefghi", and wanna "efghiabcd"
        // 1. reverse "abcd": dcba
        // 2. reverse "efghi": ihgfe
        // now chars are "dcbaihgfe"
        // 3. reverse whole chars: "dcbaihgfe" => efghiabcd
        //
        // "abcde", numberToMove=2
        reverse(chars, 0, numToMove - 1); // ab -> ba: bacde
        reverse(chars, numToMove, chars.length - 1); // cde -> edc: baedc
        reverse(chars, 0, chars.length - 1); // baedc -> cdeab
    }

    @Test
    public void testMoveCharsToEnd_3StepsReverse() {
        assertEquals("abcde", moveCharsToEnd_3StepReverse("abcde", 0));
        assertEquals("bcdea", moveCharsToEnd_3StepReverse("abcde", 1));
        assertEquals("cdeab", moveCharsToEnd_3StepReverse("abcde", 2));
        assertEquals("deabc", moveCharsToEnd_3StepReverse("abcde", 3));
        assertEquals("eabcd", moveCharsToEnd_3StepReverse("abcde", 4));
    }

    public static String moveCharsToBeginning_3StepReverse(String str, int numToMove) {
        char[] chars = str.toCharArray();
        moveCharsToEnd_3StepReverse(chars, chars.length - numToMove);
        return new String(chars);
    }

    @Test
    public void testMoveCharsToBeginning_3StepsReverse() {
        assertEquals("abcde", moveCharsToBeginning_3StepReverse("abcde", 0));
        assertEquals("eabcd", moveCharsToBeginning_3StepReverse("abcde", 1));
        assertEquals("deabc", moveCharsToBeginning_3StepReverse("abcde", 2));
        assertEquals("cdeab", moveCharsToBeginning_3StepReverse("abcde", 3));
        assertEquals("bcdea", moveCharsToBeginning_3StepReverse("abcde", 4));
    }
    // endregion

}
