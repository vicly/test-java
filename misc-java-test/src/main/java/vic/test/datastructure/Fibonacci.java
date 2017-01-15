package vic.test.datastructure;

public class Fibonacci {

	public static void main(String[] args) {
		for (int i = 1; i <=5; i ++) {
			System.out.print(forLoopImpl(i) + " ");
		}
		System.out.println();
		
		System.out.println(recursionImpl(6) + " " + recursionImpl(7) + " " + forLoopImpl(8));
	}
	
	// if N is bigger, e.g. 45, it will be very slow
	private static long recursionImpl(int n) {
		if (n <= 0) {
			return 0; 
		}
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 1;
		}
		
		return recursionImpl(n-2) + recursionImpl(n-1);
	}
	
	private static long forLoopImpl(int n) {
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 1;
		}
		long n1 = 1;
		long n2 = 1;
		for (long i = 2; i < n; i++) {
			long tmp = n1;
			n1 = n2;
			n2 = tmp + n2;
		}
		return n2;
	}
	
}
