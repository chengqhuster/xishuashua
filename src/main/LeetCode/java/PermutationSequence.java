package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/permutation-sequence/
 *
 * 思路简述：1 ... n 的第 k! + 1 个序列为 1 ... (n - k + 2) (n - k + 1) ... n
 *
 */

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        if (k < 1) {
            return "";
        }
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = i + 1;
        }
        while (k > 1) {
            int[] a = getNearestNum(k);
            int b = (k - 1)/a[1];
            k = k - a[1] * b;
            exchangeNum(num, n, a[0], b);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append(num[i]);
        }
        return sb.toString();
    }

    private int[] getNearestNum(int k) {
        int res = 1;
        int deep = 0;
        while (res < k) {
            deep++;
            res *= deep;
        }
        res =res / deep;
        return new int[]{deep - 1, res};
    }

    private void exchangeNum(int[] num, int n, int a, int b) {
        int temp = num[n - a - 1 + b];
        for (int i = n - a - 1 + b; i > n - a - 1; i--) {
            num[i] = num[i - 1];
        }
        num[n - a - 1] = temp;
    }
}
