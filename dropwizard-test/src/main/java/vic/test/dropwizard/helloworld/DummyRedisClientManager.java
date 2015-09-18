package vic.test.dropwizard.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.lifecycle.Managed;

public class DummyRedisClientManager implements Managed {

	private final static Logger LOG = LoggerFactory.getLogger(DummyRedisClientManager.class);

	@Override
	public void start() throws Exception {
		LOG.info("Initializing Redis client");
	}

	@Override
	public void stop() throws Exception {
		LOG.info("Destroying Redis client");
	}

}
