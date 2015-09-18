package vic.test.jdk;

import java.util.ArrayList;
import java.util.List;


// JDK6 is from 2011
public class Jdk7Features {


	public static void main(String[] args) {
		Jdk7Features jdk = new Jdk7Features();
		jdk.swithString();
		jdk.autoCleanStream();
		jdk.binaryInt();
		jdk.underscoreNumber();
	}

	void binaryInt() {
		int num = 0b100; // 4;
		System.out.println(num);
	}

	void underscoreNumber() {
		int tenMillions = 1_000_000; // easier for people to read
		System.out.println(tenMillions);

		// any type of number
		double d = 1000_000.0d;
		long l = 1000_000l;
		int hex = 0xdead_c0de;
		int bytes = 0x1000_0000;

		// Consecutive underscore
		int x = 100_______000;
	}


	void generic() {
		List<String> list = new ArrayList<>(); // no need ArrayList<String>;
	}

	void swithString() {
		String str = "two";
		switch(str) {
		case "one" :
			System.out.println("one matched");
			break;
		case "two" :
			System.out.println("two matched");
			break;
		case "three" :
			System.out.println("three matched");
			break;
		default :
			System.out.println("nothing matched");
		}
	}


	void multiCatch() {
		try {
			multiCatchMethod();
		} catch (ClassNotFoundException | NoSuchMethodException err) {
			err.printStackTrace();
		}
	}
	void multiCatchMethod() throws ClassNotFoundException, NoSuchMethodException {

	}


	void autoCleanStream() {
		class MyResource implements AutoCloseable {

			void read() {
				System.out.println("Consuming my resource");
				throw new IllegalStateException("Error: resource read"); // NOT stop close()
			}

			@Override
			public void close() throws Exception {
				System.out.println("Closing..");
				throw new IllegalStateException("Error: closing resource"); // always run
			}
		}

		try (MyResource r = new MyResource()) {
			r.read();
			throw new IllegalStateException("Error: after resource reading");
		} catch (Exception e) { // error from close()
			e.printStackTrace();
		}
	}

}
