package vic.test.springboot.app.jersey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Vic Liu
 */
@Component
@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Throwable> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionMapper.class);
    @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(new Error(exception.getMessage()))
                    .build();
        } else {
            LOGGER.error("Unexpected error", exception);

            throw new InternalServerErrorException("Unknown error", exception);
        }
    }
}
