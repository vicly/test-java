package vic.test.jersey.test;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import vic.test.jersey.JerseyTestApplication;
import vic.test.jersey.Task;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Vic Liu
 */
public class TaskResourceTest extends JerseyTest {
    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        return new JerseyTestApplication();
    }

    @Test
    public void testGetTasks() {
        Response response = target("tasks")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        assertThat(response.getStatus(), equalTo(200));
        assertThat(response.getMediaType(), equalTo(MediaType.APPLICATION_JSON_TYPE));

        // TODO: DEMO - Response.readEntity(..List<T>..)
        List<Task> tasks = response.readEntity(new GenericType<List<Task>>() {});
        assertThat(tasks, is(notNullValue()));
    }

    @Test
    public void testAddTask() {
        Task task = new Task();
        task.setName("TaskX");
        Entity<Task> entity = Entity.entity(task, MediaType.APPLICATION_JSON_TYPE);

        Task taskAdded = target("tasks")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(entity, Task.class);

        assertThat(taskAdded.getId(), is(notNullValue()));
        assertThat(taskAdded.getName(), equalTo(task.getName()));
    }
}
