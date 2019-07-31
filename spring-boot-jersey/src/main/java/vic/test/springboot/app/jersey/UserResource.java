package vic.test.springboot.app.jersey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

// Jersey ResourceConfig scan will wire up models from Spring, e.g. UserRepository
// No need to add @Component
@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    private UserRepository userRepository;

    @Inject
    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GET
    public Collection<User> listUsers() {
        LOGGER.info(">>>>> listUser() in ");
        return this.userRepository.findAllUsers();
    }

    @GET
    @Path("/{id}")
    public User findUserById(@PathParam("id") String id) {
        return this.userRepository.findUserById(id)
                .orElseThrow(() -> new NotFoundException("user not found by ID " + id));
    }

}
