package vic.test.jerseyswagger.resources;

import com.google.common.collect.Maps;

import org.slf4j.Logger;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import vic.test.jerseyswagger.model.User;
import vic.test.jerseyswagger.model.UserType;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Vic Liu
 */
@Api(value = "/users", description = "Operations about user")
@Path("users")
@Produces("application/json")
@Consumes("application/json")
public class UserResource {

    private static final Logger log = getLogger(UserResource.class);

    private static final Map<Long, User> users = Maps.newHashMap();
    static {
        User david = new User(1L, "David", 30, UserType.STANDARD, "1980-1-5");
        User tim = new User(2L, "Tim", 20, UserType.VIP, "1990-10-20");
        users.put(david.getId(), david);
        users.put(tim.getId(), tim);
    }

    @ApiOperation(
            notes = "`UserResource.getUserById(Long userId)`",
            value = "Get user by name",
            response = User.class,
            position = 0
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 400,
                    message = "Invalid id supplied",
                    responseHeaders = {
                            @ResponseHeader(
                                    name = "X-Rate-Limit-Limit",
                                    description = "Rate limitation"
                            ),
                            @ResponseHeader(
                                    name = "X-Rate-Limit-Remaining",
                                    description = "Rate remaining"
                            )
            }),
            @ApiResponse(code = 404, message = "User not found")
    })

    @GET
    @Path("/{userId}")
    public User getUserById(
            @ApiParam(value = "The userId")
            @PathParam("userId") Long userId) {

        log.info("Getting user, id=" + userId);

        User user = this.users.get(userId);
        return user;
    }

}
