package vic.test.temp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Vic Liu
 */
public class FindStones {

    static String findStones(int n, int a, int b) {
        if (n < 1 || n > 1000 || a < 1 || a > 1000 || b < 1 || b > 1000) {
            System.out.println("");
        }

        Set<Integer> stones = new HashSet<>();
        stones.add(0);
        for (int i = 1; i < n; i++) {
            Set<Integer> tmp = new HashSet<>();
            for (Integer x : stones) {
                int s1 = x + a;
                int s2 = x + b;
                tmp.add(s1);
                tmp.add(s2);
                stones = tmp;
            }
        }

        Integer[] arr = stones.toArray(new Integer[stones.size()]);
        Arrays.sort(arr);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                str.append(" ").append(arr[i]);
            } else {
                str.append(arr[i]);
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        if (t < 1 || t > 10) {
            scanner.close();
            System.out.println("");
        }

        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(findStones(n, a, b));
        }
    }

}
