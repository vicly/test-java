package vic.test.datastructure;

import java.util.Arrays;

/**
 * Assume array is sorted.
 *
 *
 * @author Vic Liu
 */
public class ArraySearch {

    public static void main(String[] args) {

        int[] arr = new int[] {
                3, 1, 5, 7, 6, 9
        };

        System.out.println(linearSearch(arr,9));

        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        System.out.println(binarySearch(sorted,9));

    }

    static int linearSearch(int[] a, int k) {
        if (a == null || a.length == 0) {
            return -1;
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] == k) {
                return i;
            }
        }
        return -1;
    }

    /**
     * given sorted array, find index of key.
     * 1. too small, go left
     * 2. too big, go right
     * 3. equal, found
     *
     * @param key
     */
    private static int binarySearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;

        // low == high means, only ONE element left to compare
        while (low <= high) {
            //int mid = (low + high) >>> 1;
            int mid = low + (high - low) / 2;
            int midVal = a[mid];

            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;

    }
}
