package vic.test.datastructure;

/**
 * @author Vic Liu
 */
public class Prime {

    public static void main(String[] args) {
        long n = 101;
        boolean isPrime = isPrime(n);
        if (isPrime) System.out.println(n + " is prime");
        else         System.out.println(n + " is not prime");
    }

    private static boolean isPrime(long n) {
        boolean isPrime = true;
        if (n < 2) isPrime = false;

        // try all possible factors of n
        // if if n has a factor, then it has one less than or equal to sqrt(n),
        // so for efficiency we only need to check factor <= sqrt(n) or
        // equivalently factor*factor <= n
        for (long factor = 2; factor*factor <= n; factor++) {

            // if factor divides evenly into n, n is not prime, so break out of loop
            if (n % factor == 0) {
                isPrime = false;
                break;
            }
        }

        return isPrime;
    }
}
