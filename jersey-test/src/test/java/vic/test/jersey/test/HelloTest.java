package vic.test.jersey.test;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import vic.test.jersey.HelloResource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class HelloTest extends JerseyTest {

    @Override
    protected ResourceConfig configure() {
        enable(TestProperties.LOG_TRAFFIC);
        return new ResourceConfig(HelloResource.class);
    }

    @Test
    public void testGetJson() {
        Response response = target("hello").request(MediaType.APPLICATION_JSON_TYPE).get();
        assertThat(response.getStatus(), equalTo(200));
        assertThat(response.getMediaType(), equalTo(MediaType.APPLICATION_JSON_TYPE));
    }

    @Test
    public void testGetPlainText() {
        Response response = target("hello").request(MediaType.TEXT_PLAIN_TYPE).get();
        assertThat(response.getStatus(), equalTo(200));
        assertThat(response.getMediaType(), equalTo(MediaType.TEXT_PLAIN_TYPE));
    }

}
