package vic.test.datastructure;

/**
 * 回文（指顺读和倒读都一样的词语）
 *
 */
public class Palindrome {

	public static void main(String[] args) {
		System.out.println(reverse(Long.MAX_VALUE));
		System.out.println("12321 " + (isPalindrome(12321L) ? "is palindrome" : "is not palindrome"));
		System.out.println("1232 " + (isPalindrome(1232L) ? "is palindrome" : "is not palindrome"));
	}
	
	private static boolean isPalindrome(long number) {
		return (number == reverse(number));
	}
	
	private static long reverse(long number) {
		long reverse = 0;
		while (number != 0) {
			long lastDigit = number % 10;
			reverse = reverse * 10 + lastDigit;
			number = number / 10;
		}
		return reverse;
	}
}
