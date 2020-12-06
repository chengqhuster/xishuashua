package LeetCode.java;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目描述：https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
 *
 * 思路简述：参考 MakeSumDivisibleByP
 */
public class MinimumOperationsToReduceXToZero {

    public int minOperations(int[] nums, int x) {
        int sum = 0, leftSum = 0;
        for (int num : nums) {
            sum += num;
        }
        leftSum = sum - x;
        if (leftSum == 0) {
            return nums.length;
        }
        int maxSubLen = 0, prefixSum = 0;
        Map<Integer, Integer> prefixSumFirstIndexMap = new HashMap<>();
        prefixSumFirstIndexMap.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int targetSum = prefixSum - leftSum;
            if (prefixSumFirstIndexMap.containsKey(targetSum)) {
                maxSubLen = Math.max(maxSubLen, i - prefixSumFirstIndexMap.get(targetSum));
            }
            if (!prefixSumFirstIndexMap.containsKey(prefixSum)) {
                prefixSumFirstIndexMap.put(prefixSum, i);
            }
        }
        return maxSubLen == 0 ? -1 : nums.length - maxSubLen;
    }
}
