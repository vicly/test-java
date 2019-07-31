package vic.test.springboot.app.jersey;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExitCodeApplication {

    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        return () -> 42;
    }

    public static void main(String[] args) {
        // DEMO: stop springboot with custom exit code
        System.exit(SpringApplication.exit(
                SpringApplication.run(ExitCodeApplication.class, args)
        ));
    }

}
