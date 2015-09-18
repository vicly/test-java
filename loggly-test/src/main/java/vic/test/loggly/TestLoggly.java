package vic.test.loggly;

import org.apache.log4j.Logger;

public class TestLoggly {
	
	
	public static void main(String[] args) throws InterruptedException {
		Logger logger = Logger.getLogger(TestLoggly.class);
		
		logger.info("--- BEGIN at " + System.currentTimeMillis() + " ---");
		int count = 1000;
		for (int i = 0; i < count; i++) {
			
			if ( (i % 5) == 0) {
				logger.warn("warn-msg " + i);
			}
			if ( (i % 30) == 0) {
				logger.error("error-msg " + i);
			}
			logger.info("message " + i);
			//Thread.sleep(100);
		}
		
		logger.info("--- END ---");
	}

}
