package vic.test.junit;

public class UnitPriceCalculator {
	
	public UnitPriceCalculator() {
	}
	
	public double getUnitPrice(int itemCount) {
		if (itemCount < 1) {
			throw new IllegalArgumentException();
		}

		if (itemCount < 10) {
			return 5.00d;
		} else if (itemCount >= 10 && itemCount < 50) {
			return 4.00d;
		} else {
			return 3.00d;
		}
	}

}
