package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/
 *
 * 思路简述：难点在于如何构建 dp, 数组的长度作为 dp 的维度很容易理解, 但是数组的交换与否会影响后续的数组交换
 *          因此将数组是否交换作为 dp 的另一个维度, 这样构成的 dp 状态就很容易的向后求解了
 *
 */

public class MinimumSwapsToMakeSequencesIncreasing {
    public int minSwap(int[] A, int[] B) {
        int n = A.length;
        int[] swap = new int[n];
        int[] noSwap = new int[n];
        swap[0] = 1;
        for (int i = 1; i < n; i++) {
            swap[i] = n;
            noSwap[i] = n;
            // 有解的数组一定属于下面两种情形, 且这两种情形是有重合的
            // 两种情况先后计算都可以, 后者需要用 min 函数取小解, 所以前面有定义初始值为 n
            if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                noSwap[i] = noSwap[i - 1];
                swap[i] = swap[i - 1] + 1;
            }
            if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
                noSwap[i] = Math.min(noSwap[i], swap[i - 1]);
                swap[i] = Math.min(swap[i], noSwap[i- 1] + 1);
            }
        }
        return Math.min(swap[n - 1], noSwap[n - 1]);
    }
}
