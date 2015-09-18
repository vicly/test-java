package vic.test.jdk.ws;

import javax.xml.ws.Endpoint;

public class Server {

	public static void main(String[] args) {
		System.out.println("Starting server...");

		Endpoint.publish("http://localhost:9000/helloworld", new HelloWorldImpl());
	}

}
