package vic.test.datastructure;

import com.google.common.base.Stopwatch;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

public class Sort {

	public static void main(String[] args) {
		final int SIZE = 50000;
		int[] arr = randomArray(SIZE);
		System.out.println("Sorting array with " + SIZE + " elements\n\n");

		sort("quick sort", arr, (a) -> quickSort(a));
		sort("insertion sort", arr, (a) -> insertionSort(a));
		sort("bubble sort", arr, (a) -> bubbleSort(a));
		sort("selection sort", arr, (a) -> selectionSort(a));
		sort("merge sort", arr, (a) -> mergeSort(a));
		sort("heap sort", arr, (a) -> heapSort(a));
	}

	private static void sort(String name, int[] original, Consumer<int[]> sorter) {
		Stopwatch sw = Stopwatch.createUnstarted();
		System.out.println("-----------------------> " + name);
		int[] arr = Arrays.copyOf(original, original.length);
		sw.reset().start();
		sorter.accept(arr);
		sw.stop();
		System.out.println("Time: " + sw);
	}

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

	// region bubblSort
	/**
	 * Time Complexity
	 * best:    O(n)
	 * average: O(n^2)
	 * worst:   O(n^2)
	 *
	 * Space Complexity
	 * worst:   O(1)
	 *
	 * repeatedly compare adjacent items, larger itm "bubble" right
	 *
	 * 假设从小到大排序，如果数组正好是从大到小排序，那么会导致最多次数的swap
	 *
	 */
	private static void bubbleSort(int[] arr) {
		int temp = 0;
		int n = arr.length;

		for (int i = 0; i < n; i ++) {
			for (int j = 1; j < (n - i); j++) {
				if (arr[j-1] > arr[j]) {
					temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = temp;
				}
			}
		}		
	}
	// endregion

	// region insertionSort

	/**
	 * Time Complexity (Same as bubble)
	 * best:    O(n)
	 * average: O(n^2)
	 * worst:   O(n^2)
	 *
	 * Space Complexity
	 * worst:   O(1)
	 *
	 * 5 1 6 2 4 3  (1 < 5, insert to idx 0)
	 * 1 5 6 2 4 3  (6 > 5)
	 * 1 2 5 6 4 3  (2 < 5, 2 > 1, insert to idx 1)
	 *
	 * @param arr
	 */
	static void insertionSort(int[] arr) {
		if (arr.length == 1) {
			return;
		}

		for (int i = 1, len = arr.length; i < len; i++) {
			int k = arr[i];
			int j = i - 1;
			while (j >= 0 && k < arr[j]) {
				arr[j+1] = arr[j];
				j--;
			}
			arr[j+1] = k;
		}
	}
	// endregion


	// region selectionSort
	/**
	 * O(n^2)
	 *
	 * 每轮i，查找最小element的index，与i交换
	 *
	 */
	private static void selectionSort(int[] arr) {
		for (int i = 0, n = arr.length; i < n; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
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
	// endregion

	// region quicksort
	/**
	 * Time Complexity
	 * best:    O(n log(n))
	 * average: O(n log(n))
	 * worst:   O(n^2)
	 *
	 * Space Complexity
	 * worst:   O(log(n))
	 *
	 * 简而言之，每趟使表的第一个元素放入适当位置，将表一分为二，对子表按递归方式继续这种划分，直至划分的子表长为1或0
	 */
	private static void quickSort(int[] arr) {
		if (arr.length < 2) {
			// do nothing
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
			int middle = doQuickSortPartition(arr, low, high);
			doQuickSort(arr, low, middle - 1);
			doQuickSort(arr, middle + 1, high);
		}
	}

	private static int doQuickSortPartition(int[] arr, int low, int high) {
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
	// endregion
	

	// region mergeSort

	/**
	 * !!Best sort for linked list.
	 *
	 * Time Complexity (Same as bubble)
	 * best:    O(n log(n))
	 * average: O(n log(n))
	 * worst:   O(n log(n))
	 *
	 * Space Complexity
	 * worst:   O(n)
	 *
	 *
	 * Used when data structure does not support random access, e.g. linked list.
	 *
	 */
	static void mergeSort (int[] arr) {
		doMergeSort(arr, 0, arr.length - 1);
	}

	static void doMergeSort (int[] list, int lowIndex, int highIndex) {
		if (lowIndex == highIndex)
			return;
		else {
			int midIndex = (lowIndex + highIndex) / 2;
			doMergeSort(list, lowIndex, midIndex);
			doMergeSort(list, midIndex + 1, highIndex);
			merge(list, lowIndex, midIndex, highIndex);
		}
	}

	static void merge(int[] list, int lowIndex, int midIndex, int highIndex) {
		int[] L = new int[midIndex - lowIndex + 2];

		for (int i = lowIndex; i <= midIndex; i++) {
			L[i - lowIndex] = list[i];
		}
		L[midIndex - lowIndex + 1] = Integer.MAX_VALUE;
		int[] R = new int[highIndex - midIndex + 1];

		for (int i = midIndex + 1; i <= highIndex; i++) {
			R[i - midIndex - 1] = list[i];
		}
		R[highIndex - midIndex] = Integer.MAX_VALUE;
		int i = 0, j = 0;

		for (int k = lowIndex; k <= highIndex; k++) {
			if (L[i] <= R[j]) {
				list[k] = L[i];
				i++;
			}
			else {
				list[k] = R[j];
				j++;
			}
		}
	}
	// endregion

	// region heapSort

	/**
	 * Time Complexity (Same as bubble)
	 * best:    O(n log(n))
	 * average: O(n log(n))
	 * worst:   O(n log(n))
	 *
	 * Space Complexity
	 * worst:   O(1)
	 *
	 *
	 * What? heap is a special tree
	 * 1. Complete Binary Tree ( all level of the tree are fully filled )
	 * 2. all node are either GREATER_EQUAL or LESS_EQUAL its children (Max-Heap, Min-Heap)
	 *
	 *
	 * How?
	 * 1. create a heap of the unsorted list
	 * 2. a sorted array is created by repeatedly removing the largest/smallest element from heap, and
	 *    inserting it into the array. The heap is reconstructed after each removal.
	 */
	static void heapSort(int[] a) {
		int count = a.length;

		//first place a in max-heap order
		heapify(a, count);

		int end = count - 1;
		while (end > 0) {
			//swap the root(maximum value) of the heap with the
			//last element of the heap
			int tmp = a[end];
			a[end] = a[0];
			a[0] = tmp;
			//put the heap back in max-heap order
			siftDown(a, 0, end - 1);
			//decrement the size of the heap so that the previous
			//max value will stay in its proper place
			end--;
		}
	}

	public static void heapify(int[] a, int count) {
		//start is assigned the index in a of the last parent node
		int start = (count - 2) / 2; //binary heap

		while(start >= 0){
			//shift down the node at index start to the proper place
			//such that all nodes below the start index are in heap
			//order
			siftDown(a, start, count - 1);
			start--;
		}
		//after sifting down the root all nodes/elements are in heap order
	}

	public static void siftDown(int[] a, int start, int end){
		//end represents the limit of how far down the heap to sift
		int root = start;

		while((root * 2 + 1) <= end){      //While the root has at least one child
			int child = root * 2 + 1;           //root*2+1 points to the left child
			//if the child has a sibling and the child's value is less than its sibling's...
			if(child + 1 <= end && a[child] < a[child + 1])
				child = child + 1;           //... then point to the right child instead
			if(a[root] < a[child]){     //out of max-heap order
				int tmp = a[root];
				a[root] = a[child];
				a[child] = tmp;
				root = child;                //repeat to continue sifting down the child now
			}else
				return;
		}
	}
	// endregion

}
