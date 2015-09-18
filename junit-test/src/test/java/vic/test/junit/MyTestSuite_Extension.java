package vic.test.junit;

import junit.framework.TestResult;
import junit.framework.TestSuite;



public class MyTestSuite_Extension extends TestSuite {

	public MyTestSuite_Extension() {
		super(UnitPriceCalculatorEdgeTest.class, CalculatorTest.class);
	}
	
	public static void main(String[] args) {
		MyTestSuite_Extension suite = new MyTestSuite_Extension();
		TestResult result = new TestResult();
		suite.run(result);
		System.out.println("number of test cases = " + result.runCount());
	}
	
}
