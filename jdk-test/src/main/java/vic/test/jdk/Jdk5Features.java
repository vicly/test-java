package vic.test.jdk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// JDK5 is from 2004
public class Jdk5Features {


	public static void main(String[] args) {
		Jdk5Features jdk = new Jdk5Features();
		jdk.forLoop();
		jdk.variableArguments("amount", 1, 2, 3);
		jdk.variableArguments("amount");
		jdk.systemOutFormat();
		jdk.userInputScanner();
	}

	void generic() {
		List<String> stringList = new ArrayList<String>();
	}

	void forLoop() {
		System.out.println("---------------For Loop-----------------");

		List<String> list = new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		for (String item : list) {
			System.out.println(item);
		}

		int[] numbers = {10, 20, 30};
		for (int num : numbers) {
			System.out.println(num);
		}
	}

	void variableArguments(String name, int...numbers) {
		System.out.println("----------------Variable Arguments----------------");
		int total = 0;
		for (int num : numbers) {
			total += num;
		}
		System.out.println(name + ": " + total);
	}

	void staticImport() {
		// import static Xyz.methodX;
	}

	void autoboxing() {
		Integer num = Integer.valueOf(5);
		int intNum = num; // auto, Obj to primitive
		num = intNum + 1; // vise versa
	}

	void annotation() {
		// @xx
	}

	enum Color { BLACK, WHITE, RED };
	void enums() {
		// enum is class, and extends java.lang.Enum
		System.out.println("---------------Enum-----------------");
		for (Color c : Color.values()) {
			System.out.println(c);
		}
	}

	void systemOutFormat() {
		System.out.println("---------------system.out.format-----------------");
		System.out.format("Hello, %s. %TF", "Vic", new Date());
	}

	void userInputScanner() {
		System.out.println("---------------Scanner-----------------");
		System.out.print("Please input a number:");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		System.out.println("You inputed " + num);
	}
}
