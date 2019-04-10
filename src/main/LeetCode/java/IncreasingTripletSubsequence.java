package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/increasing-triplet-subsequence/
 *
 * 思路简述：参考 LongestIncreasingSubsequence
 *
 */

import java.util.Arrays;

public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int[] seq = new int[2];
        int len = 0;
        for (int num : nums) {
            int index  = Arrays.binarySearch(seq, 0, len, num);
            if (index < 0) {
                index = -(index + 1);
            }
            if (index == 2) {
                return true;
            } else {
                seq[index] = num;
                if (index == len) {
                    len++;
                }
            }
        }
        return false;
    }
}
