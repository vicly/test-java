package vic.test.jdk.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkAndJoin {
	/*
	 * fork-join framework is as of JDK7
	 *
	 * allow to distribute tasks on several workers and then wait for the result.
	 *
	 */

	public static void main(String[] args) {

		class Sum extends RecursiveTask<Integer> {
			private static final long serialVersionUID = 1L;

			final static int SEQUENTIAL_THRESHOLD = 2;
			int[] array;
			int low;
			int high;

			Sum(int[] array) {
				this(array, 0, array.length);
			}

			Sum(int[] array, int low, int high) {
				this.array = array;
				this.low = low;
				this.high = high;
			}
			@Override
			protected Integer compute() {
				if (high - low <= SEQUENTIAL_THRESHOLD) {
					System.out.format("> calculating: low=%s, high=%s\n", low, high);
					int sum = 0;
					for (int i = low; i < high; i++) {
						sum += array[i];
					}
					return sum;
				} else {
					// split
					int mid = low + (high - low) / 2;
					Sum left = new Sum(array, low, mid);
					Sum right = new Sum(array, mid, high);
					left.fork();
					return left.join() + right.compute();
				}
			}
		}


		final int[] array = new int[] {
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14
		};
		int expectedSum = 0;
		for (int n : array) {
			expectedSum += n;
		}
		System.out.println("Expected sum is " + expectedSum);

		ForkJoinPool pool = new ForkJoinPool();
		System.out.println("Actual sum is " + pool.invoke(new Sum(array)));

	}

}
