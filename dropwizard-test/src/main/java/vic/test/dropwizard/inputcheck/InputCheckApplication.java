package vic.test.dropwizard.inputcheck;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 *
 * @author Vic Liu
 */
public class InputCheckApplication extends Application<InputCheckConfiguration> {

    public static void main(String[] args) throws Exception {
        new InputCheckApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<InputCheckConfiguration> bootstrap) {
        super.initialize(bootstrap);

        bootstrap.addBundle(new SwaggerBundle<InputCheckConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(InputCheckConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });

        bootstrap.getObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void run(InputCheckConfiguration configuration, Environment environment) throws Exception {

        environment.jersey().register(UserResource.class);

    }
}
