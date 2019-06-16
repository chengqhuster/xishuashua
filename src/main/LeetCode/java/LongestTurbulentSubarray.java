package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/longest-turbulent-subarray/
 *
 * 思路简述：easy job
 *
 */

public class LongestTurbulentSubarray {
    public int maxTurbulenceSize(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int res = 1, cur = 1, flag = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] == A[i - 1]) {
                res = Math.max(res, cur);
                cur = 1;
                flag = 0;
            } else if (A[i] > A[i - 1]) {
                if (flag != 1) {
                    cur++;
                } else {
                    res = Math.max(res, cur);
                    cur = 2;
                }
                flag = 1;
            } else {
                if (flag != -1) {
                    cur++;
                } else {
                    res = Math.max(res, cur);
                    cur = 2;
                }
                flag = -1;
            }
        }
        return Math.max(res, cur);
    }
}
