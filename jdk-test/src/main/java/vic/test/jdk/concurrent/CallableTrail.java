package vic.test.jdk.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTrail {

	public static void main(String[] args) {

		class PlusTask implements Callable<String> {
			int val1, val2;
			PlusTask(int val1, int val2) {
				this.val1 = val1;
				this.val2 = val2;
			}
			@Override
			public String call() throws Exception {
				return String.format("%s + %s = %s", val1, val2, (val1 + val2));
			}
		}

		ExecutorService executor = Executors.newFixedThreadPool(3);
		List<Future<String>> list = new ArrayList<>();
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			int val1 = r.nextInt(10000);
			int val2 = r.nextInt(10000);
			Callable<String> plus = new PlusTask(val1, val2);
			Future<String> result = executor.submit(plus);
			list.add(result);
		}

		System.out.println(">> list.size = " + list.size());

		for (Future<String> future : list) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		executor.shutdown();
		while (!executor.isTerminated()) {
		}

		System.out.println(">> Finished all threads");

	}

}
