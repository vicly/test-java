package vic.test.datastructure;

import java.math.BigInteger;
import java.util.stream.LongStream;

/**
 * 乘阶.
 *
 * 5 => 1 * 2 * 3 * 4 * 5 => 120
 *
 * @author Vic Liu
 */
public class Factorial {

    public static void main(String[] args) {
        System.out.println("5: " + forLoopImpl(5));
        System.out.println("21: " + forLoopImpl(21)); // wrong, n must <= 20
        System.out.println("5: " + recursionImpl(5));
        System.out.println("5: " + streamImpl(5));
        System.out.println("5: " + bigIntegerImpl(5));
        System.out.println("21: " + bigIntegerImpl(21));
    }

    static long forLoopImpl(long n) {
        long r = 1;
        for (int i = 1; i <= n; i++) {
            r *= i;
        }
        return r;
    }

    static long recursionImpl(long n) {
        return n == 1 ? 1 : n * recursionImpl(n - 1);
    }

    static long streamImpl(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(1, (a, b) -> a * b);
    }

    // Hanlde n > 20
    static BigInteger bigIntegerImpl(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
    }
}
