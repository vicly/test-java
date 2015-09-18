package vic.test.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnitPriceCalculatorEdgeTest {

	private UnitPriceCalculator upc;

	@Before
	public void setUp() {
		this.upc = new UnitPriceCalculator();
	}

	@After
	public void tearDown() {
		this.upc = null;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testZeroItemCount() {
		upc.getUnitPrice(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegtiveItemCount() {
		upc.getUnitPrice(-1);
	}
	
}
