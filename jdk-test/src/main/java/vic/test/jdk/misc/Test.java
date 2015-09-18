package vic.test.jdk.misc;

public class Test {
	
	public static void main(String[] args) {
		
		A a = new B();
		a.doStuff();
		
	}

}


class A {
	void doStuff() {
		System.out.println("A");
	}
	
}
class B extends A {
	void doStuff() {
		super.doStuff();
		System.out.println("B");
	}	
}