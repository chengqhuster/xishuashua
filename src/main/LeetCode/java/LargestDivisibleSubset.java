package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/largest-divisible-subset/
 *
 * 思路简述：dp 二层循环
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset {
   public List<Integer> largestDivisibleSubset(int[] nums) {
       List<Integer> res = new ArrayList<>();
       if (nums == null || nums.length == 0) {
           return res;
       }
       Arrays.sort(nums);
       int len = nums.length;
       int[] dp = new int[len];
       int[] index = new int[len];
       for (int i = 0; i < len; i++) {
           dp[i] = 1;
           index[i] = i;
       }
       int maxIndex = 0;
       int maxGroupSize = Integer.MIN_VALUE;
       for (int i = 1; i < len; i++) {
           for (int j = 0; j < i; j++) {
               if (nums[i] % nums[j] == 0) {
                   if (dp[j] + 1 > dp[i]) {
                       dp[i] = dp[j] + 1;
                       index[i] = j;
                       if (dp[i] > maxGroupSize) {
                           maxGroupSize = dp[i];
                           maxIndex = i;
                       }
                   }
               }
           }
       }
       while (index[maxIndex] != maxIndex) {
           res.add(nums[maxIndex]);
           maxIndex = index[maxIndex];
       }
       res.add(nums[maxIndex]);
       return res;
    }
}
