package vic.test.springboot.app.greeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication, add all
//   @Configuration
//   @EnableWebMvc, when spring-webmvc on classpath
//   @EnableAutoConfiguration
//   @ComponentScan in current package
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
