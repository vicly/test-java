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
        return new ResourceConfig(GsonProvider.class, HelloResource.class);
    }

    @Test
    public void testConnection() {
        System.out.println("/hello request...");
        Response response = target("hello").request(MediaType.APPLICATION_JSON_TYPE).get();
        System.out.println("/hello response: " + response);
        assertEquals(200, response.getStatus());

        String resString;
        System.out.println("/hello request...");
        resString = target("hello")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(String.class);
        System.out.println("/hello response: " + resString);

        System.out.println("/hello/task request...");
        resString = target("hello/task")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(String.class);
        System.out.println("/hello/task response: " + resString);
    }

}
