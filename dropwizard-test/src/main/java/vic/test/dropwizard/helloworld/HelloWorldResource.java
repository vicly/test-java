package vic.test.dropwizard.helloworld;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * resource class
 */
@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldResource.class);
	/*
	 * this class is used by multiple thread.
	 */

	private final String template;

	private final String defaultName;

	private final AtomicLong counter;

	public HelloWorldResource(String template, String defaultName) {
		this.template = template;
		this.defaultName = defaultName;
		this.counter = new AtomicLong();
	}

	@GET
	@Timed
	public Saying sayHello(@QueryParam("name") Optional<String> name) {
		final String value = String.format(template, name.or(defaultName));

		LOGGER.info("saying hello");
		LOGGER.info("saying \"cvv\"  : \"123\"");

		return new Saying(counter.incrementAndGet(), value);
	}

//	@GET
//	@CacheControl(maxAge = 10, maxAgeUnit = TimeUnit.HOURS)
//	public String getDefaultName() {
//		return defaultName;
//	}
}
