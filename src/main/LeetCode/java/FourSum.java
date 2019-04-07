package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/4sum/
 *
 * 思路简述：参考 ThreeSum，每次循环增加判断 提前 break
 *
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {
            if (nums[i] * 4 > target) {
                break;
            }
            if (i > 0 && nums[i] == nums[i  -1]) {
                continue;
            }
            for (int j = i + 1; j < len - 2; j++) {
                if (nums[i] + nums[j] * 3 > target) {
                    break;
                }
                if (j > i + 1 && nums[j] == nums[j  -1]) {
                    continue;
                }
                int left = j + 1;
                int right  = len - 1;
                int t = target - nums[i] - nums[j];
                while (left < right) {
                    int sum = nums[left] + nums[right];
                    if (t == sum) {
                        List<Integer> slot = new LinkedList<>();
                        slot.add(nums[i]);
                        slot.add(nums[j]);
                        slot.add(nums[left]);
                        slot.add(nums[right]);
                        res.add(slot);
                        do {
                            left++;
                        } while (left < right && nums[left - 1] == nums[left]);
                    } else if (t > sum) {
                        left++;
                        if (nums[left] * 2 > t) {
                            break;
                        }
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}
