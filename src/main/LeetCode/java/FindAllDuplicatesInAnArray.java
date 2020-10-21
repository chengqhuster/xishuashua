package LeetCode.java;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目描述：https://leetcode.com/problems/find-all-duplicates-in-an-array/
 *
 * 思路简述：
 */
public class FindAllDuplicatesInAnArray {

    public List<Integer> findDuplicates(int[] nums) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] == i + 1) {
                continue;
            }
            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (nums[i] != i + 1) {
                res.add(nums[i]);
            }
        }
        return res;
    }
}
