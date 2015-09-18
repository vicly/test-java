package vic.test.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("hello")
public class HelloResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "hello";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sayHelloJson() {
		return "{\"message\":\"hello json\"}";
	}
	
	@Path("task")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Task task() {
		System.out.println("----> task()");
		Task task = new Task();
		task.setId("T001");
		task.setName("Detial design");
		return task;
	}
	
	
	@Path("nothingFound")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response nothingFound() {
		return Response.noContent().build();
	}
	
	@Path("found")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response found() {
		Task task = new Task();
		task.setId("T001");
		task.setName("Detial design");
		Response res = Response.ok(task).build();
		return res;
	}
	
}
