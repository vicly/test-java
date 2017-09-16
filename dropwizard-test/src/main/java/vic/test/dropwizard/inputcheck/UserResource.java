package vic.test.dropwizard.inputcheck;

import io.dropwizard.jersey.params.UUIDParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Vic Liu
 */
@Api
@Path("/users")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class UserResource {

    /*

    @PathParam

    UUID userId
    {"code": 404, "message": "HTTP 404 Not Found"}

    UUIDParam:
    { "code": 400, "message": "path param userId is not a UUID."}



    >>> body

    int age; or IntParam age

    age: 123   => pass
    age: "123" => pass
    age: "abc" => "code": 400, "message": "Unable to process JSON"

    SO: as long as exception occurred, Jackson exception thrown, and 400


    @NotEmpty String name

    name: "" or name: null or missing =>
        {"errors": [
            "name may not be empty"
        ]}


    NonEmptyStringParam name;
    name: "" or name: null or missing

    */





    @PUT
    @Path("/{userId}")
    @ApiOperation("updateUser")
    public User updateUser(@PathParam("userId") UUIDParam userId, @Valid User userToUpdate) {
        return userToUpdate;
    }

}
