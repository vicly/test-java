package vic.test.jdk.ws;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class Client {

	public static void main(String[] args) throws Exception {
		URL wsdl = new URL("http://localhost:9000/helloworld?wsdl");
		QName serviceName = new QName("http://ws.jdk.test.vic/", "HelloWorld");
		Service service = Service.create(wsdl, serviceName);
		HelloWorld helloWorld = service.getPort(HelloWorld.class);
		System.out.println("WS message : " + helloWorld.sayHello("Vic"));
	}

}
