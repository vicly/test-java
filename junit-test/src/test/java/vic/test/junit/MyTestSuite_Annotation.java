package vic.test.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CalculatorTest.class,
	UnitPriceCalculatorEdgeTest.class,
	LongTimeTest.class,
	UnitPriceCalculatorNormalTest.class
})
public class MyTestSuite_Annotation {}
