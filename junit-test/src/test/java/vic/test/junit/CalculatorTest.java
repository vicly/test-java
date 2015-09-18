package vic.test.junit;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

	private Calculator calculator;

	@Before
	public void setUp() {
		this.calculator = new Calculator();
	}

	@After
	public void tearDown() {
		this.calculator = null;
	}

	@Test
	public void testPlus() {
		int result = this.calculator.plus(2, 3);
		assertEquals(5, result);
	}

}
