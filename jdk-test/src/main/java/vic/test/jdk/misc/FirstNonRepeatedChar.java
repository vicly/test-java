package vic.test.jdk.misc;

import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatedChar {

	public static void main(String[] args) {
		
		String str = "teet`e 3t";
		
		if (str == null) {
			System.out.println("invalid str");
			return;
		}
		
		char[] chars = str.toCharArray();
		if (chars.length == 0) {
			System.out.println("empty str");
			return;
		}
		
		
		Map<Character, Integer> charMap = new HashMap<Character, Integer>();
		
		Character c;
		for (int i = 0; i < chars.length; i++) {
			c = chars[i];
			if (charMap.containsKey(c)) {
				charMap.put(c, (charMap.get(c) + 1));
			} else {
				charMap.put(c, 1);
			}
		}
		
		for (char ch : chars) {
			if (charMap.get(ch) == 1) {
				System.out.println("found: '" + ch + "'");
				return;
			}
		}

		System.out.println("Not found");
	}
	
}
