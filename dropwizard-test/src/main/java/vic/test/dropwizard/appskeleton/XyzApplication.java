package vic.test.dropwizard.appskeleton;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import vic.test.dropwizard.helloworld.HelloWorldApplication;
import vic.test.dropwizard.helloworld.HelloWorldConfiguration;

import com.codahale.metrics.health.HealthCheck;

public class XyzApplication extends Application<HelloWorldConfiguration> {

	public static void main(String[] args) throws Exception {
		new HelloWorldApplication().run(args);
	}

	@Override
	public String getName() {
		return "xyz";
	}

	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
		System.out.println(">> initializing ...");
	}

	@Override
	public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
		System.out.println(">> running ...");

		final Object resource = null;
		final HealthCheck healthCheck = null;
		environment.jersey().register(resource);
		environment.healthChecks().register("aName", healthCheck);
	}

}