package vic.test.dropwizard.helloworld;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

	public static void main(String[] args) throws Exception {

		args =  new String[] { "server", "/home/vic/PHL/tech-test/dropwizard-test/hello-world.yml" };

		new HelloWorldApplication().run(args);
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	/*
	 * Bootstrap phase
	 */
	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
		System.out.println(">> bootstrapping ...");
		bootstrap.addCommand(new DummyCommand("dryrun", "dry run"));
	}

	@Override
	public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
		System.out.println(">> running ...");

		final HelloWorldResource resource = new HelloWorldResource(
			configuration.getTemplate(),
			configuration.getDefaultName()
		);
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.jersey().register(resource);
		environment.healthChecks().register("template", healthCheck);

		environment.lifecycle().manage(new DummyRedisClientManager());

	}

}
