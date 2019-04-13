package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/permutations-ii/
 *
 * 思路简述：参考 Permutations，数组先进行排序
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsII {
    boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        visited = new boolean[nums.length];
        permuteUniqueCore(nums, res, new ArrayList<>());
        return res;
    }

    private void permuteUniqueCore(int[] nums, List<List<Integer>> res, List<Integer> temp) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
        } else {
            boolean addedFlag = false;
            int last = 0;
            for (int i = 0; i < nums.length; i++) {
                if (!visited[i] && (!addedFlag || nums[i] != last)) {
                    addedFlag = true;
                    last = nums[i];
                    temp.add(nums[i]);
                    visited[i] = true;
                    permuteUniqueCore(nums, res, temp);
                    // pay attention here
                    temp.remove(temp.size() - 1);
                    visited[i] = false;
                }
            }
        }
    }
}
