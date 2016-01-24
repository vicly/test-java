package vic.test.jersey;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Vic Liu
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {

        if (exception instanceof WebApplicationException) {
            WebApplicationException webApplicationException = WebApplicationException.class.cast(exception);
            Response rawResponse = webApplicationException.getResponse();
            return Response.fromResponse(rawResponse)
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .entity("reason: " + exception.getLocalizedMessage())
                    .build();
        }

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.TEXT_PLAIN_TYPE)
                .entity("reason: " + exception.getLocalizedMessage())
                .build();
    }
}
