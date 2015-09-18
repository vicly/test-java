package vic.test.ws.hello.client;

import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import vic.test.ws.hello.server.HelloWorld;
import vic.test.ws.hello.server.User;
import vic.test.ws.hello.server.UserImpl;

public class Client {
	private static final QName SERVICE_NAME = new QName("http://server.hello.ws.test.vic/", "HelloWorld");
	private static final QName PORT_NAME = new QName("http://server.hello.ws.test.vic/", "HelloWorldPort");

	private Client() {
	}

	public static void main(String args[]) throws Exception {
		// Endpoint Address
		String endpointAddress = "http://localhost:9000/helloWorld"; // see Server.class
		// For web container: web.xml-> services; cxf-servlet.xml -> hello_world
//		endpointAddress = "http://localhost:9000/ws-test/services/hello_world";
//		endpointAddress = "http://localhost:9000/ws-test/services/helloWorldEndpoint";

/*		URL wsdl = new URL(endpointAddress + "?wsdl");
		Service theService = Service.create(wsdl, SERVICE_NAME);
		HelloWorld helloWorld = theService.getPort(HelloWorld.class);
		System.out.println("WS message : " + helloWorld.sayHi("Vic"));
		System.out.println("-----------------------------------------");
*/


		Service service = Service.create(SERVICE_NAME);
		service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

		HelloWorld hw = service.getPort(HelloWorld.class);

		System.out.println(hw.sayHi("Vic Liu"));
		System.out.println(hw.sayHiToUser(new UserImpl("World")));
		System.out.println(hw.sayHiToUser(new UserImpl("Galaxy")));
		System.out.println(hw.sayHiToUser(new UserImpl("Universe")));

		System.out.println();
		System.out.println("Users: ");
		Map<Integer, User> users = hw.getUsers();
		for (Map.Entry<Integer, User> e : users.entrySet()) {
			System.out.println("  " + e.getKey() + ": " + e.getValue().getName());
		}

	}
}
