package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/continuous-subarray-sum/
 *
 * 思路简述：前缀和取模, 模相等说明存在解, 问题要求至少两个连续元素的和, 因此需要记录前缀位置
 *
 */

import java.util.HashMap;

public class ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int mod = 0;
        map.put(mod, - 1);
        for (int i = 0; i < nums.length; i++) {
            if (k == 0) {
                mod += nums[i];
            } else {
                mod = (mod + nums[i]) % k;
            }
            if (map.containsKey(mod)) {
                int val = map.get(mod);
                if (i > val + 1) {
                    return true;
                }
            } else {
                map.put(mod, i);
            }
        }
        return false;
    }
}
