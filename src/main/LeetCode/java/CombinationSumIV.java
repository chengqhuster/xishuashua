package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/combination-sum-iv/
 *
 * 思路简述：dp = dfs + mem
 *
 */

import java.util.Arrays;

public class CombinationSumIV {
   public int combinationSum4(int[] nums, int target) {
       int[] dp = new int[target + 1];
       Arrays.fill(dp, -1);
       dp[0] = 1;
       return helper(nums, dp, target);
   }

   private int helper(int[] nums, int[] dp, int target) {
       if (dp[target] != -1) {
           return dp[target];
       }
       int count = 0;
       for (int i = 0; i < nums.length; i++) {
           if (target >= nums[i]) {
               count += helper(nums, dp, target - nums[i]);
           }
       }
       dp[target] = count;
       return count;
   }
}
