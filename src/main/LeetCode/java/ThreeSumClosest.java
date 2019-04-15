package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/3sum-closest/
 *
 * 思路简述：参考 ThreeSum
 *
 */

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int res = 0;
        int mindis = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            int sum = target - nums[i];
            while (left < right) {
                int diff = nums[left] + nums[right] - sum;
                if (diff == 0) {
                    return target;
                } else {
                    if (Math.abs(diff) < mindis) {
                        mindis = Math.abs(diff);
                        res = target + diff;
                    }
                    if (diff > 0) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return res;
    }
}
