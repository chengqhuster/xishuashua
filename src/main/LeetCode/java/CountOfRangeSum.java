package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/count-of-range-sum/
 *
 * 思路简述：参考 CountOfSmallerNumbersAfterSelf 2
 *          preSum[j] - preSum[i] 的结果对应的序列包括 i 不包括 j
 *
 *          merge 的时候，对于 left 的每个 i，计算出 right 部分第一个满足 preSum[index] - preSum[i] 大于等于 lower 的 k，以及
 *          第一个不满足 preSum[j] - preSum[i] 小于 upper 的 j，则对于 i 开头的序列满足要求的个数有 j - k 个
 *
 */

public class CountOfRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        return mergeSort(preSum, 0, nums.length + 1, lower, upper);
    }

    private int mergeSort(long[] preSum, int start, int end, int lower, int upper) {
        if (end - start <= 1) {
            return 0;
        }
        int mid = (start + end) / 2;
        int count = mergeSort(preSum, start, mid, lower, upper)
                + mergeSort(preSum, mid, end, lower, upper);
        int j = mid, k = mid, t = mid;
        long[] temp = new long[end - start];
        // 对于每个 i，j k 的位置不用从 mid 开始计算，接着上次的位置往后判断即可
        for (int i = start, r = 0; i < mid; i++, r++) {
            // 排序
            while (t < end && preSum[t] < preSum[i]) {
                temp[r++] = preSum[t++];
            }
            temp[r] = preSum[i];

            // 统计
            while (k < end && preSum[k] - preSum[i] < lower) {
                k++;
            }
            while (j < end && preSum[j] - preSum[i] <= upper) {
                j++;
            }
            count += j - k;
        }
        System.arraycopy(temp, 0, preSum, start, t - start);
        return count;
    }
}
