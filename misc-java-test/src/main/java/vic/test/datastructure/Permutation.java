package vic.test.datastructure;

import java.util.HashSet;
import java.util.Set;

/**
 * 排列组合
 * @author Vic Liu
 */
public class Permutation {

    // ABC => ABC, BAC, BCA, ACB, CAB, CBA

    public static Set<String> permutationFinder(String str) {
        Set<String> perm = new HashSet<>();
        if (str == null) {
            return null;
        } else if (str.length() == 0) {
            // not-empty set with single "" element, ensure below for loop works for last char,
            // "C" + set("") => C
            // "B" + set("C") => BC, CB
            // "A" + set("BC", "CB") => ABC, BAC, BCA, ACB, CAB, CBA
            perm.add("");
            return perm;
        }

        char initial = str.charAt(0);
        String rem = str.substring(1);
        System.out.println("[" + str + "] init=" + initial + ", rem=" + rem);

        Set<String> words = permutationFinder(rem);
        System.out.println("[" + str + "] words=" + words);

        for (String word : words) {
            for (int i = 0; i <= word.length(); i++) {
                perm.add(charInsert(word, initial, i));
            }
        }
        System.out.println("[" + str + "] << " + perm);


        return perm;
    }

    private static String charInsert(String str, char c, int j) {
        String begin = str.substring(0, j);
        String end = str.substring(j);
        return begin + c + end;
    }

    public static void main(String[] args) {
        System.out.println("ABC => " + permutationFinder("ABC"));
    }

}
