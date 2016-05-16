package vic.test.jerseyswagger;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.Contact;
import io.swagger.models.Info;
import io.swagger.models.License;

/**
 * @author Vic Liu
 */
final class SwaggerUtil {
    static void config() {
        Info info = new Info()
                .title("Swagger test app")
                .description("This is a test application to try swagger and jersey")
                .termsOfService("http://foo.com/terms/")
                .contact(new Contact().email("apiteam@foo.com"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html"));

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[] { "http" });
        beanConfig.setHost("localhost:9090");
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage("vic.test.jerseyswagger.resources");
        beanConfig.setScan(true);
        beanConfig.setInfo(info);
        beanConfig.setTermsOfServiceUrl("terms-new");
    }
}
