package vic.test.junit;

import org.junit.Test;

public class LongTimeTest {
	
	// if 800, case fails
	@Test(timeout=1300)
	public void testDoBigJobDuration() {
		LongTime lt = new LongTime();
		lt.doBigJob();
	}

}
