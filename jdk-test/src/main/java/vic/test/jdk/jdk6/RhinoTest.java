package vic.test.jdk.jdk6;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class RhinoTest {

	/*
	 * Rhino is JS script engine comes with Java 6.
	 * Run JS from java and JS from command line
	 *
	 */
	public static void main(String[] args) {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");

		String script =
				"var str = 'hello vic';"
				+ "print(str);";

		try {
			engine.eval(script);
		} catch (ScriptException e) {
			e.printStackTrace();
		}

	}

}
