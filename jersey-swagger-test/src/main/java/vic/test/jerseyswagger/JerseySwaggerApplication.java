package vic.test.jerseyswagger;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Vic Liu
 */
public class JerseySwaggerApplication extends ResourceConfig {

    public JerseySwaggerApplication() {

        register(JacksonFeature.class);

        /*
	<servlet>
		<servlet-name>jerseyJaxrsConfig</servlet-name>
		<servlet-class>io.swagger.jersey.config.JerseyJaxrsConfig</servlet-class>
		<init-param>
			<param-name>api.version</param-name>
			<param-value>1.0.0</param-value>
		</init-param>
		<init-param>
			<param-name>swagger.api.basepath</param-name>
			<param-value>http://localhost:9090/api</param-value>
		</init-param>
		<load-on-startup>2</load-on-startup>
	</servlet>

    <servlet>
        <servlet-name>swaggerBootstrap</servlet-name>
        <servlet-class>vic.test.jerseyswagger.SwaggerBootstrap</servlet-class>
        <load-on-startup>2</load-on-startup>
    </servlet>
         */
        SwaggerUtil.config();


        /* Option 1: scan package
    <servlet>
        <servlet-name>jersey</servlet-name>
        <servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
        <init-param>
            <param-name>jersey.config.server.provider.packages</param-name>
            <param-value>
                io.swagger.jaxrs.listing,
                {your.application.packages}
            </param-value>
        </init-param>
    </servlet>
         */
        packages(
                // swagger listing API
                "io.swagger.jaxrs.listing",
                // your code
                "vic.test.jerseyswagger.resources"
        );

        /* Option2: explicitly specify classes
    <servlet>
        <servlet-name>jersey</servlet-name>
        <servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
        <init-param>
            <param-name>jersey.config.server.provider.classnames</param-name>
            <param-value>
                io.swagger.jaxrs.listing.AcceptHeaderApiListingResource,
                io.swagger.jaxrs.listing.ApiListingResource,
                io.swagger.jaxrs.listing.SwaggerSerializers,
                {your.application.classes}
            </param-value>
        </init-param>
    </servlet>
        register(io.swagger.jaxrs.listing.AcceptHeaderApiListingResource.class); // /swagger
        register(io.swagger.jaxrs.listing.ApiListingResource.class); // swagger.json|yaml
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
         */

    }
}
