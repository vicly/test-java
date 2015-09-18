package vic.test.jdk.misc;

public class Java7ResourceManagement {
	
	public static void main(String[] args) {
		
		try {
//			normalThrow();
			tryWithResource();
		} catch (Exception e) {
			System.out.println("Exception Got: " + e.getMessage());
		}
		
	}
	
	static void tryWithResource() throws Exception {
		try (MyResource r = new MyResource("y")) {
			System.out.println("tryWithResource.code.run");
			throw new Exception("tryWithResource.code.exception");
		}
	}
	
	static void normalThrow() throws Exception {
		MyResource r = new MyResource("x");
		try {
			System.out.println("normalThrow.code.run");
			r.close();
		} catch (Exception e) {
			throw e;
		} finally {
			throw new Exception("normalThrow.final.exception");
		}
	}

}

class MyResource implements AutoCloseable {
	private String name;
	MyResource(String name) {
		this.name = name;
	}
	
	@Override
	public void close() throws Exception {
		System.out.println("MyResource." + this.name + ".close()");
		throw new Exception("MyResource." + this.name + ".exception");
	}
	
}