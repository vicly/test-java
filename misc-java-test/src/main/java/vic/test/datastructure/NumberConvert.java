package vic.test.datastructure;

import java.util.Arrays;

/**
 * @author Vic Liu
 */
public class NumberConvert {

    public static void main(String[] args) {
        System.out.println(binaryToDecimal("1101")); // 13
        System.out.println(octalToDecimal("12"));     // 10
        System.out.println(hexToDecimal("FF"));        // 255
        System.out.println(decimalToHex(2500));             // A

    }

    /*
    Int -> B

      整数部分
         n 除以2，得商p1，余y1
         p1除以2，得商p2，余y2
         ...至到商为0

         读数：从下往上收集余数，即：y2 y1

      小数部分
         方法：乘2取整法，即将小数部分乘以2，然后取整数部分，剩下的小数部分继续乘以2，然后取整数部分，剩下的小数部分又乘以2，一直取到小数部分

        将0.125换算为二进制（0.001）
        第一步，将0.125乘以2，      得0.25, 则整数部分为0, 小数部分为0.25;
        第二步, 将小数部分0.25乘以2, 得0.5, 则整数部分为0, 小数部分为0.5;
        第三步, 将小数部分0.5乘以2,  得1.0, 则整数部分为1, 小数部分为0.0;
        第四步, 读数,从第一位读起,读到最后一位,即为0.001。

    Int -> O

       小数部分：
          方法：乘8取整法，即将小数部分乘以8，然后取整数部分，
               剩下的小数部分继续乘以8，然后取整数部分，剩下的小数部分又乘以8，
               一直取到小数部分为零为止。
               如果永远不能为零，就同十进制数的四舍五入一样，暂取个名字叫3舍4入。


    B -> O

        11010111.0100111
        按小数点分组，三个一组
        11 010 111.010 011 1
        不够三位补0
        011 010 111.010 011 100
        从左到右读数
        3   2   7  .2   3   4
        得到
        327.234


    O -> B
        327
        变成3位一组
        3    2   7
        011 010 111
        从左到右读数
        11010111

    H <-> B
       与上面一样，只是按 4 位分组

    O -> H  只能先转位二进制，再转
       O -> B -> H
       H -> B -> O

     */


    // region to int
    static int binaryToDecimal(String binary) {
        char[] c = {'0', '1'};
        return toDecimal(c, binary);
    }

    static int octalToDecimal(String octal) {
        char[] c = {'0', '1', '2', '3', '4', '5', '6', '7'};
        return toDecimal(c, octal);
    }

    static int hexToDecimal(String hex) {
        char[] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        return toDecimal(c, hex);
    }

    static int toDecimal(char[] dict, String n) {
        char[] chars = n.toCharArray();
        int base = dict.length;
        int result = 0;
        int maxIdx = chars.length - 1;
        for (int i = maxIdx; i >= 0; i--) {
            int digit = Arrays.binarySearch(dict, chars[i]); // index => digit
            if (digit == -1) {
                throw new IllegalArgumentException("Invalid digit: " + String.valueOf(chars[i]));
            }
            result += (digit * Math.pow(base, maxIdx - i));
        }
        return result;
    }
    // endregion

    // region int -> X
    static String decimalToHex(int n) {
        // 除以 2， 得商quotient， 余数remainder
        char[] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        if (n == 0) return "0";

        StringBuilder sb = new StringBuilder();

        int radix = c.length;
        int q = n;
        int r;
        while (q != 0) {
            r = q % radix;
            q = q / radix;
            sb.append(c[r]);
        }

        return sb.reverse().toString();

    }

    // endregion



}
