package vic.test.springboot.app.jersey;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Vic Liu
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ResourceConfig resourceConfig() {
        return new ResourceConfig()
                .packages("vic.test.springboot.app.jersey")
                //.register(UserResource.class)
                //.register(DefaultExceptionMapper.class)
                .register(new LoggingFeature(
                        Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                        Level.INFO,
                        LoggingFeature.DEFAULT_VERBOSITY, null)
                );
    }
}
