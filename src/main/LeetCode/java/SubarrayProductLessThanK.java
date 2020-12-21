package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/subarray-product-less-than-k/
 *
 * 思路简述：滑动窗口, 对连续的 1 要特殊处理
 */
public class SubarrayProductLessThanK {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int p = 0, res = 0, q, product;
        while (p < nums.length) {
            // 前置连续的 1 的数量
            int prefixOnes = 0;
            while (p + 1 < nums.length && nums[p] * nums[p + 1] == 1) {
                prefixOnes += 1;
                p += 1;
            }

            if (prefixOnes > 0 && k > 1) {
                res += prefixOnes * (prefixOnes + 1) / 2;
            }

            product = 1;
            q = p;
            while (q < nums.length) {
                product *= nums[q];
                if (product < k) {
                    res += prefixOnes + 1;
                    q += 1;
                } else {
                    break;
                }
            }
            p += 1;
        }
        return res;
    }
}
