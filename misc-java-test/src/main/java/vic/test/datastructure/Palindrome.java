package vic.test.datastructure;

public class Palindrome {

	public static void main(String[] args) {
		System.out.println(reverse(Long.MAX_VALUE));
		System.out.println(isPalindrome(12321L));
		System.out.println(isPalindrome(1232L));
	}
	
	private static boolean isPalindrome(long number) {
		return (number == reverse(number));
	}
	
	private static long reverse(long number) {
		long reverse = 0;
		while (number != 0) {
			reverse = reverse * 10 + number % 10;
			number = number / 10;
		}
		return reverse;
	}
}
