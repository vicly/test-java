package vic.test.datastructure;

import java.util.Arrays;
import java.util.Random;

import com.google.common.base.Stopwatch;

public class Sort {

	public static void main(String[] args) {
		final int SIZE = 10000;
		int[] original = randomArray(SIZE);
		int[] arr = null;
		
		
		System.out.println("Sorting array with " + SIZE + " elements");
		Stopwatch sw = Stopwatch.createUnstarted();
		
		System.out.println("-----------------------> bubble sort");
		arr = Arrays.copyOf(original, original.length);
		sw.start();
		bubbleSort(arr);
		sw.stop();
		System.out.println("Time: " + sw);
		
		
		
		System.out.println("-----------------------> choice sort");
		arr = Arrays.copyOf(original, original.length);
		sw.reset().start();
		choiceSort(arr);
		sw.stop();
		System.out.println("Time: " + sw);
		
		
		System.out.println("-----------------------> quick sort");
		arr = Arrays.copyOf(original, original.length); // 10w(25ms) 100w(130ms) 1000w(1282ms), 10000w(15sec)
		sw.reset().start();
		quickSort(arr);
		sw.stop();
		assertSmallToBig(arr);
		System.out.println("Time: " + sw);
		
	}
	

	/**
	 * O(n^2)
	 * 
	 * 这个实现是从小到大排序，如果数组正好是从大到小排序，那么会导致最多次数的swap
	 */
	private static void bubbleSort(int[] arr) {
		int tmp = 0;
		for (int i = 0, len = arr.length; i < len; i ++) {
			for (int j = i + 1; j < len; j++) {
				if (arr[i] > arr[j]) { // keep changing arr[i], make it smallest
					tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}		
	}
	
	/**
	 * O(n^2) 
	 */
	private static void choiceSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[min] > arr[j]) {
					min = j;
				}
			}
			
			if (min != i) {
				int tmp = arr[i];
				arr[i] = arr[min];
				arr[min] = tmp;
			}
		}
	}
	
	/**
	 * 平均时间复杂度是O(nlogn)
	 */
	private static void quickSort(int[] arr) {
		if (arr.length < 2) {
		} else if (arr.length == 2) {
			if (arr[0] > arr[1]) {
				int tmp = arr[0];
				arr[0] = arr[1];
				arr[1] = tmp;
			}
		} else {
			doQuickSort(arr, 0, arr.length - 1);
		}
	}

	private static void doQuickSort(int[] arr, int low, int high) {
		if (low < high) {
			int middle = quickSortGetMiddle(arr, low, high);
			doQuickSort(arr, low, middle - 1);
			doQuickSort(arr, middle + 1, high);
		}
	}

	private static int quickSortGetMiddle(int[] arr, int low, int high) {
		int p = arr[low];
		while (low < high) {
			// right to left, first smaller one
			while (low < high && arr[high] >= p) {
				high--;
			}
			arr[low] = arr[high];
			// left to right, first bigger one
			while (low < high && arr[low] <= p) {
				low++;
			}
			arr[high] = arr[low];
		}
		arr[low] = p;
		return low;
	}
	
	
	
	
	
	// ------------------------
	private static int[] randomArray(int length) {
		Random r = new Random();
		int[] arr = new int[length];
		for (int i = 0; i < length; i++) {
			arr[i] = r.nextInt(length);
		}
		return arr;
	}
	
	private static void assertSmallToBig(int[] arr) {
		for (int i = 0; i < arr.length - 2; i++) {
			if (arr[i] > arr[i+1]) {
				throw new RuntimeException(String.format("idx %s, cur=%s, nxt=%s", i, arr[i], arr[i+1]));
			}
		}
	}
	
}
