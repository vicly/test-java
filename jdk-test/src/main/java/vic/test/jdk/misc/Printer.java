package vic.test.jdk.misc;

import java.io.Serializable;

public class Printer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void print(int intVal) {
		System.out.println("single integer");
	}
	
	public void print(int intVal, int intVal2) {
		System.out.println("2 integers");
	}
	
	public void print(long longVal) {
		System.out.println("single long");
	}
	
	public static void main(String[] args) {
		Printer p = new Printer();
		p.print(123); // single integer
		p.print(123, 123); // 2 integers
		p.print(123L); // single long
	}
}
