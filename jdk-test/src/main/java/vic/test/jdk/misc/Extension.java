package vic.test.jdk.misc;

public class Extension {

	public static void main(String[] args) {
//		new MySuper();
//		System.out.println("--------------");
		MyChild obj = new MyChild();
		new MyChild();
		
		obj.m1();
		
		MySuper su = obj;
		// su.m1(); // BAD: must try catch
		

		System.out.println("---------------");
		obj.m2("hello");    // m2.string
		obj.m2("hele", 1L); // m2.string+obj[]
		obj.m2("he", "h2"); // m2.string[]
		System.out.println();
		
		obj.m2(1);          // m2.int
		obj.m2(new Integer(1)); // m2.Integer
		obj.m2(1L);             // m2.long
		obj.m2(Long.valueOf(1));// m2.LONG
		
		System.out.println();
		obj.m2((byte) 122); // m2.short !! When no m2(byte), then short is closest
		obj.m2(new Byte((byte) 12));
		
		System.out.println();
		Object data = "xs";
		obj.m2(data); // m2.String
		// obj.m2(data, data); // compile error: m2.obj[], and m2.obj+obj[] matched 
	}
	
	public static void main(String[] args, String...strings ) {
		
	}
	
}

class MySuper {
	static {
		System.out.println("MySuper.static");
	}
	
	public MySuper() {
		System.out.println("MySuper.constructor");
	}
	
	protected void m1() throws SuperException {
		System.out.println("MySuper.m1()");
	}
	
	void m2(Object obj) {
		System.out.println("m2.Obj");
	}
	
	void m2(Object... obj) {
		System.out.println("m2.Obj[]");
	}
	
	void m2(Object obj, Object...objects) {
		System.out.println("m2.Obj+obj[]");
	}
	
	void m2(String str) {
		System.out.println("m2.String");
	}
	
	void m2(String...strings) {
		System.out.println("m2.String[]");
	}
	
	void m2(String obj, Object...objects) {
		System.out.println("m2.String+obj[]");
	}
	
	void m2(Number nu) {
		System.out.println("m2.Number");
	}
	
//	void m2(byte b) {
//		System.out.println("m2.byte");
//	}
	
	void m2(short i) {
		System.out.println("m2.short");
	}

	void m2(int i) {
		System.out.println("m2.int");
	}
	
	void m2(Integer i) {
		System.out.println("m2.Integer");
	}
	
	void m2(long l) {
		System.out.println("m2.long");
	}
	
	void m2(Long l) {
		System.out.println("m2.LONG");
	}
	
}


class MyChild extends MySuper {
	static {
		System.out.println("MyChild.static");
	}
	
	public MyChild() {
		System.out.println("MyChild.constructor");
	}
	
	// Override:
	// 1. visibility: can NOT reduce visibility
	// 2. throws:
	//    1. MySuper's exception or its sub exception
	//    2. GOOD: remove 'throws": protected void m1() {} 
	protected void m1() {
	// OK:  public/protected
	// BAD: <def>/private
					//  OK:  throws SuperException
					//  OK:  throws ChildException
					//  OK:  <no throws>
					//  BAD: throws Exception
		System.out.println("MyChild.m1()");
	}
}

class SuperException extends Exception {}
class ChildException extends SuperException {}
