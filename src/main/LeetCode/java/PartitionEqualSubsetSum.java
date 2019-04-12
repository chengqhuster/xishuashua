package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/partition-equal-subset-sum/
 *
 * 思路简述：1. 参考 PartitionToKEqualSumSubsets，（从前往后回溯时超时，排序后大元素在后面，集合小，dfs的深度低）
 *
 */

import java.util.Arrays;

public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        sum = sum >> 1;
        Arrays.sort(nums);
        return canPartition(nums, new int[nums.length], nums.length - 1, 0, sum);
    }

    private boolean canPartition(int[] nums, int[] visited, int start, int sum, int target) {
        if (sum == target) {
            return true;
        }
        for (int i = start; i >= 0; i--) {
            if (visited[i] == 0) {
                visited[i] = 1;
                int temp = sum + nums[i];
                if (temp > target) {
                    visited[i] = 0;
                    break;
                }
                if (canPartition(nums, visited, i - 1, temp, target)) {
                    return true;
                }
                visited[i] = 0;
            }
        }
        return false;
    }
}
