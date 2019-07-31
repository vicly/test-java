package vic.test.springboot.app.jersey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
public class Example {

    private static final Logger LOGGER = LoggerFactory.getLogger(Example.class);

    private ApplicationArguments applicationArguments;

    private ExampleConfiguration exampleConfiguration;

    // DEMO: inject ApplicationArguments
    @Autowired
    public Example(ApplicationArguments applicationArguments, ExampleConfiguration exampleConfiguration) {
        this.applicationArguments = applicationArguments;
        this.exampleConfiguration = exampleConfiguration;

        System.out.println(">>>> " + this.exampleConfiguration);
    }
//
//    @RequestMapping("/error")
//    @ResponseBody
//    public ResponseEntity<ErrorModel> error() {
//        ErrorModel error = new ErrorModel();
//        error.setCode("404");
//        error.setMessage("Default 404");
//
//        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
//    }
//
//    public static class ErrorModel {
//        private String code;
//        private String message;
//
//        public String getCode() {
//            return code;
//        }
//
//        public void setCode(String code) {
//            this.code = code;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//        }
//    }

    @RequestMapping("/")
    String home() {
        LOGGER.debug("home() - in");

        if (this.exampleConfiguration.isEnabled()) {
            return this.exampleConfiguration.getGreeting()
                    + " "
                    + this.exampleConfiguration.getSecurity().getUsername()
                    + " " + this.exampleConfiguration.getSecurity().getRoles()
                    + " - " + this.exampleConfiguration.toString();
        } else {
            return "Hi Guest";
        }
    }

    @RequestMapping("/appArgs")
    String appArgs() {
        return Arrays.stream(this.applicationArguments.getSourceArgs()).collect(Collectors.joining(", "));
    }

}
