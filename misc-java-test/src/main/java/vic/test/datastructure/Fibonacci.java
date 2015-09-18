package vic.test.datastructure;

public class Fibonacci {

	public static void main(String[] args) {
		for (int i = 1; i <=50; i ++) {
			System.out.print(f2(i) + " ");
		}
		System.out.println();
		
		System.out.println(f(6) + " " + f(7) + " " + f2(8));
	}
	
	// if N is bigger, e.g. 45, it will be very slow
	private static long f(int n) {
		if (n <= 0) {
			return 0; 
		}
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 1;
		}
		
		return f(n-2) + f(n-1);
	}
	
	private static long f2(int n) {
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 1;
		}
		long tmp = 0;
		long n1 = 1;
		long n2 = 1;
		for (long i = 2; i < n; i++) {
			tmp = n1;
			n1 = n2;
			n2 = tmp + n2;
		}
		return n2;
	}
	
}
