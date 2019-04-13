package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/permutations/
 *
 * 思路简述：采用回溯的方式遍历
 *
 */

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        permuteCore(nums, res, new ArrayList<>());
        return res;
    }

    private void permuteCore(int[] nums, List<List<Integer>> res, List<Integer> temp) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
        } else {
            for (int num : nums) {
                if (!temp.contains(num)) {
                    temp.add(num);
                    permuteCore(nums, res, temp);
                    // pay attention here
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }
}
