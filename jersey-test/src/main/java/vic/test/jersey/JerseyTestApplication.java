package vic.test.jersey;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyTestApplication extends ResourceConfig {

    public JerseyTestApplication() {
        packages("vic.test.jersey");
        register(JacksonFeature.class);
    }


    private static final URI BASE_URI = URI.create("http://localhost:8080/base/");
    public static final String ROOT_PATH = "helloworld";

    public static void main(String[] args) {
        try {
            System.out.println("\"Hello World\" Jersey Example App");
            final ResourceConfig resourceConfig = new ResourceConfig(HelloResource.class);
            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig);
            System.out.println(String.format("Application started.\nTry out %s%s\nHit enter to stop it...", BASE_URI, ROOT_PATH));
            System.in.read();
            server.shutdownNow();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

    }
}
