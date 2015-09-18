package vic.test.jdk.process;

import java.io.IOException;
import java.util.Arrays;

public class ExecInThread {

	public static void main(String[] args) throws Exception {

		ExecCallback callback = new ExecCallback() {

			long callerThreadId = Thread.currentThread().getId();

			@Override
			public void exitWithError(Exception error) {
				System.out.println(String.format("Main %s: subprocess failes with error", callerThreadId));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
				}
				error.printStackTrace();
			}

			@Override
			public void exitTimeout() {
				System.out.println(String.format("Main %s: subprocess failes due to timeout", callerThreadId));
			}

			@Override
			public void exitComplete(int exitCode) {
				System.out.println(String.format("Main %s: subprocess completes: %s", callerThreadId, exitCode));
			}
		};

		// String[] cmd = {"/bin/sh", "-c", "ls -l"};
		//String[] cmd = { "/bin/sh", "-c", "java -version" };
		//String[] cmd = {"/bin/sh", "-c", "/opt/jdk1.7.0_45/bin/java -version"};
		String[] cmd = {"/bin/sh", "-c", "sleep 3"};
		Exec exec = new Exec(callback, cmd, 2000);
		new Thread(exec).start();

		System.out.println(String.format("Main %s: Doing something else", Thread.currentThread().getId()));
		Thread.sleep(1000);
		System.out.println(String.format("Main %s: DONE", Thread.currentThread().getId()));
	}
}

class Exec implements Runnable {

	private final static long CHECK_INTERVAL = 50;
	private ExecCallback callback;
	private long allowedMaxExecTime;
	private String[] command;

	Exec(ExecCallback callback, String[] command, long allowedMaxExecTime) {
		this.callback = callback;
		this.command = command;
		this.allowedMaxExecTime = allowedMaxExecTime;
	}

	@Override
	public void run() {
		final boolean MERGE_TO_STANDARD_OUTPUT = true;
		ProcessBuilder pb = new ProcessBuilder(this.command);
		pb.redirectErrorStream(MERGE_TO_STANDARD_OUTPUT);
		Process p = null;
		try {
			echo("Executing command %s", Arrays.toString(this.command));
			p = pb.start();
			waitFor(p);
			int exitCode = p.exitValue();
			echo("Complete: %s", exitCode);
			callback.exitComplete(exitCode);
		} catch (IOException | InterruptedException e) {
			callback.exitWithError(e);
		} catch (IllegalThreadStateException e) {
			echo("Reached max exec time(%s), killing process", this.allowedMaxExecTime);
			kill(p);
			callback.exitTimeout();
		} catch (Exception unknown) {
			echo("Unknown error occurs, killing process");
			kill(p);
			callback.exitWithError(unknown);
		} finally {
			echo("Exit code: %s", p.exitValue());
		}
	}

	/**
	 * Wait till allowedMaxExecTime reached or process complete
	 * @throws InterruptedException  if any thread has interrupted the current thread.
	 * The interrupted status of the current thread is cleared when this exception is thrown
	 */
	private void waitFor(Process p) throws InterruptedException {
		long start = System.currentTimeMillis();
		long elapsed = 0;
		while ((elapsed = System.currentTimeMillis() - start) < this.allowedMaxExecTime && !isComplete(p)) {
			Thread.sleep(CHECK_INTERVAL);
		}
	}

	private boolean isComplete(Process p) {
		try {
			p.exitValue();
			return true;
		} catch (IllegalThreadStateException itse) {
			return false;
		}
	}

	private void kill(Process process) {
		if (process == null) {
			return;
		}
		try {
			process.destroy();
		} catch (Throwable anyErr) {
			// TODO: get string of stack, print it at once;
			echo("Failed to kill process");
			anyErr.printStackTrace();
		}
	}

	private void echo(String format, Object... args) {
		String msg = "";
		if (args != null && args.length > 0) {
			msg = String.format(format, args);
		}
		System.out.println(String.format("[T%s] %s", Thread.currentThread().getId(), msg));
	}

}

interface ExecCallback {
	/**
	 * called after subprocess naturally completed
	 */
	void exitComplete(int exitCode);

	/**
	 * called after subprocess reaches maxAllowedExecTime and is killed
	 */
	void exitTimeout();

	/**
	 * called when any unexpected error occurs
	 */
	void exitWithError(Exception error);
}