package vic.test.jdk.jdk6;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class MathFunctions {
	public double plus(double a, double b) {
		return a + b;
	}

	public static void main(String[] args) {
		// go to http://localhost:8080/mathfunctions?WSDL
		Endpoint.publish("http://localhost:8080/mathfunctions", new MathFunctions());
		System.out.println("published web service MathFunctions");
	}
}