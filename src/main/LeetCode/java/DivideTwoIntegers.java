package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/divide-two-integers/
 *
 * 思路简述：移位运算
 */
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        // 特例......
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean flag = (dividend <= 0 && divisor <= 0) || (dividend >= 0 && divisor >= 0);
        long dividendL = Math.abs((long)dividend);
        long divisorL = Math.abs((long)divisor);
        long res = 0;
        while (dividendL >= divisorL) {
            long[] partAns = getPartAns(dividendL, divisorL);
            res += partAns[0];
            dividendL = partAns[1];
        }
        return flag ? (int) res : (int) -res;
    }

    private long[] getPartAns(long dividend, long divisor) {
        int res = 1;
        while (true) {
            long temp = divisor << 1;
            if (temp > dividend) {
                return new long[]{res, dividend - divisor};
            } else {
                res <<= 1;
                divisor = temp;
            }
        }
    }
}
