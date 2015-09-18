package vic.test.junit;

// to test timeout
public class LongTime {

	public void doBigJob() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
