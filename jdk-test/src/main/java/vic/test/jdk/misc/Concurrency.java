package vic.test.jdk.misc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Concurrency {
  private static final int NTHREDS = 10;

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
    for (int i = 0; i < 500; i++) {
      Runnable worker = new MyRunnable(String.valueOf(i), 10000000L + i);
      executor.execute(worker);
    }
    executor.shutdown();
    executor.awaitTermination(10, TimeUnit.SECONDS);
    System.out.println("Finished all threads");
  }
}

class MyRunnable implements Runnable {
	  private final long countUntil;
	  private String name;
	  MyRunnable(String name, long countUntil) {
		this.name = name;
	    this.countUntil = countUntil;
	  }

	  @Override
	  public void run() {
	    long sum = 0;
	    for (long i = 1; i < countUntil; i++) {
	      sum += i;
	    }
	    System.out.println(this.name + ": " + sum);
	  }
	} 	