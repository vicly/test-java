package vic.test.jersey.test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import vic.test.jersey.GsonProvider;
import vic.test.jersey.HelloResource;


public class HelloTest extends JerseyTest {

	@Override
	protected ResourceConfig configure() {
		enable(TestProperties.LOG_TRAFFIC);
		return new ResourceConfig(
				GsonProvider.class,
				HelloResource.class);
	}
	
	@Test
	public void testConnection() {
		Response response = target().path("hello").request(MediaType.APPLICATION_JSON_TYPE).get();
		assertEquals(200, response.getStatus());

		String resString;
		
		resString = target().path("hello").request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
		System.out.println(resString);

		resString = target().path("hello/task").request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
		System.out.println(resString);
	}
	
}


