package vic.test.junit;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class UnitPriceCalculatorNormalTest {

	private UnitPriceCalculator upc;
	private int input;
	private double expected;

	public UnitPriceCalculatorNormalTest(int input, double expected) {
		this.input = input;
		this.expected = expected;
	}
	
	@Before
	public void setUp() {
		this.upc = new UnitPriceCalculator();
	}

	@After
	public void tearDown() {
		this.upc = null;
	}

	@Parameters
	public static List<Integer[]> testData() {
		return Arrays.asList(new Integer[][] {
				{1, 5},
				{5, 5},
				{9, 5},
				{11, 4},
				{15, 4},
				{25, 4},
				{35, 4},
				{45, 4},
				{49, 4},
				{50, 3},
				{60, 3},
				{1000, 3}
		});
	}
	
	@Test
	public void testUnitPrice() {
		double delta = 0.01;
		assertEquals(this.expected, upc.getUnitPrice(this.input), delta);
	}

	
}
