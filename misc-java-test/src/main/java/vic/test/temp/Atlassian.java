package vic.test.temp;

/**
 * @author Vic Liu
 */
public class Atlassian {

    public static void main(String[] args) {
        //
        // find
        int[] arr1 = { 1, 5, 2, 5, 2, 3};
        int[] arr2 = { 1, 5, 2, 3 };
        System.out.println(findFirstIndex(arr1, arr2));

        //
        // validate input
        //
        byte[] validInput = new byte[] {0x7E, 0x20, 0x7D, 0x21};
        try {
            validateInput(validInput);
            System.out.println("validateInput: valid data - pass");
        } catch (IllegalArgumentException e) {
            System.out.println("validateInput: valid data - failed: " + e.getMessage());
        }

        byte[] inValidInput = new byte[] {0x7F};
        try {
            validateInput(inValidInput);
            System.out.println("validateInput: invalid data failed, should throw exception");
        } catch (IllegalArgumentException e) {
            System.out.println("validateInput: invalid data - pass, got expected exception: " + e.getMessage());
        }


        System.out.println(Integer.toHexString(15));
        System.out.println(Integer.toHexString(-15));

    }




    private static int findFirstIndex(int[] arr, int[] sub) {
        final int NOT_FOUND = -1;

        if (arr == sub) {
            return 0;
        }

        if (arr.length < sub.length || sub.length == 0) {
            return NOT_FOUND;
        }

        int result = NOT_FOUND;
        int maxSubIdx = sub.length - 1;
        int diff = arr.length - sub.length;
        for (int i = 0, j = 0; i < arr.length; i++) {
            boolean leftElementsNotEnough = (i - j) > diff;
            if (leftElementsNotEnough) {
                return NOT_FOUND;
            }

            if (arr[i] == sub[j]) {
                if (j == 0) result = i;
                if (j == maxSubIdx) return result;
                j++;
            } else {
                if (result != NOT_FOUND) {
                    i --;
                    j = 0;
                    result = NOT_FOUND;
                }
            }
        }
        return result;
    }

    /**
     * char is ASCII
     * valid char: between 0x20 and 0x7E
     */
    private static void validateInput(byte[] input) {
        final byte from = 0x20;
        final byte to = 0x7E;
        for (int i = 0; i < input.length; i++) {
            if (input[i] < from || input[i] > to) {
                throw new IllegalArgumentException("Invalid char: " + Integer.toHexString(input[i]));
            }
        }
    }


    private static String encodeBase7(int number) {
        char[] b7 = {'0', 'a', 't', 'l', 's', 'o', 'n'};

        //Integer.toHexString(50);
        return null;
    }

}
