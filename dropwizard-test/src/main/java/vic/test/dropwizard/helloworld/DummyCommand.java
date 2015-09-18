package vic.test.dropwizard.helloworld;

import net.sourceforge.argparse4j.inf.Namespace;
import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;

public class DummyCommand extends ConfiguredCommand<HelloWorldConfiguration> {

	protected DummyCommand(String name, String description) {
		super(name, description);
	}

	@Override
	protected void run(
			Bootstrap<HelloWorldConfiguration> bootstrap,
			Namespace namespace,
			HelloWorldConfiguration configuration)
	throws Exception {
		System.out.println(">> DummyCommand is triggered");
	}

}
