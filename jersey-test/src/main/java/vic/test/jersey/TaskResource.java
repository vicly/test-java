package vic.test.jersey;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("tasks")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class TaskResource {

	private Tasks tasks = new Tasks();

	@GET
	@Path("{id}")
	public Task getTask(@PathParam("id") String id) {
		Optional<Task> t = this.tasks.getTask(id);
		if (t.isPresent()) {
			return t.get();
		}
		throw new NotFoundException();
	}

	@GET
	public List<Task> findAll() {
		return this.tasks.findAll();
	}

    @POST
    public Task addTask(Task task) {
        if (task == null) {
            throw new BadRequestException("No task provided");
        }
        if (task.getName() == null || task.getName().trim().isEmpty()) {
            throw new BadRequestException("Task name must be provided");
        }

        this.tasks.addTask(task);
        String id = task.getId();
        Optional<Task> getBack = tasks.getTask(id);
        if (getBack.isPresent()) {
            return getBack.get();
        }
        throw new InternalServerErrorException("");
    }

}
