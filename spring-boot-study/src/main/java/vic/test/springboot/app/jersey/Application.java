package vic.test.springboot.app.jersey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author Vic Liu
 */
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableConfigurationProperties({
        ExampleConfiguration.class
})
public class Application {

    public static void main(String[] args) {
        // SpringApplication.run(Application.class, args);
        SpringApplication application = new SpringApplication(Application.class);

        application.addListeners((ApplicationListener<ApplicationEvent>) event ->
                System.out.println("\nAppEvent: " + event.getTimestamp() + " - " + event + "\n"));

        application.run(args);
    }
}
