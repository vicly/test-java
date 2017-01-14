package vic.test.temp;

public class StringCompress {
	
	public static void main(String[] args) {
		System.out.println("aaabbb => " + compress("aaabbb"));
	}

	public static String compress(String str) {

		StringBuilder sb = new StringBuilder();
		char[] chars = str.toCharArray();
		char c = 0;
		int num = 0;
		for (int i = 0; i < chars.length; i++) {
			if (sb.length() == chars.length) {
				// compressed even longer
				// use original string
				return str;
			}

			if (i == 0) {
				c = chars[i];
				num ++;
			} else {
				if (c == chars[i]) {
					num++;
				} else {
					sb.append(c).append(num);
					c = chars[i];
				}
			}
		}

		return sb.toString();
	}

}

