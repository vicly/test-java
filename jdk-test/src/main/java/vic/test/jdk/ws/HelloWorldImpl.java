package vic.test.jdk.ws;

import javax.jws.WebService;

@WebService(endpointInterface = "vic.test.jdk.ws.HelloWorld", serviceName = "HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}

}
