package vic.test.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("hello")
public class HelloResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String sayHelloJson() {
        return "{\"message\":\"hello json\"}";
    }

    /**
     * Matched when "accept: text/plain"
     */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "hello";
	}

}
