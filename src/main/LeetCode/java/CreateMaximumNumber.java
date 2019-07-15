package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/create-maximum-number/
 *
 * 思路简述：https://leetcode.com/problems/create-maximum-number/discuss/77285/Share-my-greedy-solution
 *
 */

import java.util.Arrays;

public class CreateMaximumNumber {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;
        int[] res = new int[k];
        for (int i = Math.max(0, k - m); i <= k && i <= n; i++) {
            int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i));
            if (bigger(candidate, 0, res, 0)) {
                res = candidate;
            }
        }
        return res;
    }

    private boolean bigger(int[] a, int i, int[] b, int j) {
        while (i < a.length && j < b.length && a[i] == b[j]) {
            i++;
            j++;
        }
        return j == b.length || (i < a.length && a[i] > b[j]);
    }

    private int[] merge(int[] a, int[] b) {
        int n = a.length, m = b.length, i = 0, j = 0;
        int[] res = new int[m + n];
        while (i + j < m + n) {
            if (bigger(a, i, b , j)) {
                res[i + j] = a[i++];
            } else {
                res[i + j] = b[j++];
            }
        }
        return res;
    }

    private int[] maxArray(int[] num, int k) {
        int n = num.length;
        int[] res = new int[k];
        for (int i = 0, j = 0; i < n; i++) {
            while (j + n - i > k && j > 0 && res[j - 1] < num[i]) {
                j--;
            }
            if (j < k) {
                res[j++] = num[i];
            }
        }
        return res;
    }
}
