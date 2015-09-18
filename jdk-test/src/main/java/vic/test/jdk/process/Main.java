package vic.test.jdk.process;

import java.io.IOException;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		// String[] cmd = {"/bin/sh", "-c", "ls -l"};
		//String[] cmd = { "/bin/sh", "-c", "java -version" };
		// String[] cmd = {"/bin/sh", "-c",
		// "/opt/jdk1.7.0_45/bin/java -version"};
		System.out.println(String.format("Main %s: fork", Thread.currentThread().getId()));
		String[] cmd = {"/bin/sh", "-c", "sleep 3"};

		final boolean MERGE_TO_STANDARD_OUTPUT = true;
		ProcessBuilder pb = new ProcessBuilder(cmd);
		pb.redirectErrorStream(MERGE_TO_STANDARD_OUTPUT);
		Process p = null;
		try {
			System.out.println(String.format("T%s.executing: %s", Thread
					.currentThread().getId(), Arrays.toString(cmd)));
			p = pb.start();
			int exitCode = p.waitFor();
			System.out.println(String.format("T%s.complete: %s", Thread.currentThread().getId(), exitCode));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			p.destroy();
		}
		/*
		 * String line; System.out.println("<Error>"); BufferedReader
		 * stderr = new BufferedReader(new
		 * InputStreamReader(p.getErrorStream())); while ((line =
		 * stderr.readLine()) != null) { System.out.println(line); }
		 * System.out.println("</Error>");
		 *
		 * System.out.println("<Std>"); BufferedReader input = new
		 * BufferedReader(new InputStreamReader(p.getInputStream()));
		 * while ((line = input.readLine()) != null) {
		 * System.out.println(line); } input.close();
		 * System.out.println("</Std>");
		 */
	}
}
