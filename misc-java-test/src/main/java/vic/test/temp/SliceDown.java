package vic.test.temp;

/**
 * Give an array, find smallest one, and minus it for the others, print non-zero
 * till get 1 element left.
 *
 * input: 4, 3, 5, 8, 12
 *
 * 1, 2, 5, 9  ( -3 )
 * 1, 4, 8     ( -1 )
 * 3, 7        ( -1 )
 * 4           ( -3 )
 * @author Vic Liu
 */
public class SliceDown {
    public static void main(String[] args) {
        sliceDown(4, 3, 5, 8, 12);
    }

    static void sliceDown(int... arr) {
        if (arr.length == 1) {
            System.out.println(arr[0]);
            return;
        }

        int s = smallestPositiveNumber(arr);
        while (s > 0) {
            boolean anyPrint = false;
            for (int i = 0; i < arr.length; i++) {
                int tmp = arr[i] - s;
                arr[i] = tmp;
                if (tmp > 0) {
                    System.out.print(tmp + " ");
                    anyPrint = true;
                }
            }
            if (anyPrint) {
                System.out.println();
            }
            s = smallestPositiveNumber(arr);
        }
    }

    /**
     * @return 0 if not found
     */
    static int smallestPositiveNumber(int[] arr) {
        int r = 0;
        for (int i = 0; i < arr.length; i++) {
            int v = arr[i];
            if (v <= 0) continue;
            if (r > v || r == 0) r = v;
        }
        return r;
    }

}
