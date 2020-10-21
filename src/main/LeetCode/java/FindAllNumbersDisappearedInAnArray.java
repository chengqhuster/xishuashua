package LeetCode.java;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目描述：https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 *
 * 思路简述：
 */
public class FindAllNumbersDisappearedInAnArray {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int size = nums.length;
        for (int i = 1; i <= size; i++) {
            if (nums[i - 1] == i) {
                continue;
            }
            while (nums[i - 1] != i && nums[nums[i - 1] - 1] != nums[i - 1]) {
                int temp = nums[nums[i - 1] - 1];
                nums[nums[i - 1] - 1] = nums[i - 1];
                nums[i - 1] = temp;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            if (nums[i - 1] != i) {
                res.add(i);
            }
        }
        return res;
    }
}
