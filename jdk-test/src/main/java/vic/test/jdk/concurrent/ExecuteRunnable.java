package vic.test.jdk.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecuteRunnable {

	public static void main(String[] args) {
		final int THREAD_NUMBER = 3;
		final int JOBS = 20;

		ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUMBER);

		//
		// Runnable does NOT return result. If you need it, use Callable
		//
		class TestJob implements Runnable {
			String name;
			TestJob(String name) {
				this.name = name;
			}
			@Override
			public void run() {
				System.out.format("Thread %2s is working on job %s\n", Thread.currentThread().getId(), name);
			}
		};

		for (int i = 0; i < JOBS; i ++) {
			String name = String.valueOf(i + 1);
			executor.execute(new TestJob(name));
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executor.shutdown();
		try {
			executor.awaitTermination(2000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Finished all threads");

	}
}
