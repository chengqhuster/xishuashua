package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 *
 * 思路简述：回溯的方式，blank 的作用是判断当前集合是否为空集（sum 为 0 时需要判断）
 *
 */

public class PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        sum = sum / k;
        return canPartition(nums, new int[nums.length], 0, k, 0, true, sum);
    }

    private boolean canPartition(int[] nums, int[] visited, int start, int k, int sum, boolean blank, int target) {
        if (k == 1) {
            return true;
        }
        if (sum == target && !blank) {
            return canPartition(nums, visited, 0, k - 1, 0, true, target);
        }
        for (int i = start; i < nums.length; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                if (canPartition(nums, visited, i + 1, k, sum + nums[i], false, target)) {
                    return true;
                }
                visited[i] = 0;
            }
        }
        return false;
    }
}
